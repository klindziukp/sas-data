package com.klindziuk.sas.flux.data.layer.service.impl;

import com.klindziuk.sas.flux.data.config.StreamSetsConfig;
import com.klindziuk.sas.flux.data.constant.StreamSetsHeader;
import com.klindziuk.sas.flux.data.layer.service.StreamSetsService;
import com.klindziuk.sas.flux.data.mapper.WeatherMapper;
import com.klindziuk.sas.flux.data.model.repository.WeatherHash;
import com.klindziuk.sas.flux.data.model.request.StreamSetsWeatherRequest;
import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import com.klindziuk.sas.flux.data.model.response.streamsets.StreamSetsResponse;
import com.klindziuk.sas.flux.data.layer.repository.impl.WeatherHashRedisRepositoryImpl;
import java.util.UUID;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StreamSetsServiceImpl implements StreamSetsService {

  private final StreamSetsConfig streamSetsConfig;
  private final WeatherHashRedisRepositoryImpl weatherHashRepository;
  private final WeatherMapper weatherMapper;
  private final WebClient webClient;

  @Autowired
  public StreamSetsServiceImpl(StreamSetsConfig streamSetsConfig,
      WeatherHashRedisRepositoryImpl weatherHashRepository,
      WeatherMapper weatherMapper,
      WebClient webClient) {
    this.streamSetsConfig = streamSetsConfig;
    this.weatherHashRepository = weatherHashRepository;
    this.weatherMapper = weatherMapper;
    this.webClient = webClient;
  }

  @Override
  public Function<OpenWeatherResponse, Mono<StreamSetsResponse>> provideStreamSetsResponse() {
    return openWeatherResponse -> {
      final StreamSetsWeatherRequest weatherEntity = weatherMapper.toWeatherRequest(
          openWeatherResponse);
      final int hashCode = weatherEntity.hashCode();
      return weatherHashRepository.findByHash(hashCode)
          .doOnNext(redisHashItem -> {
            log.info("Making call to StreamSets to save DUPLICATE Entity: {}", weatherEntity);
            weatherEntity.setDuplicate(true);
          })
          .switchIfEmpty(Mono.defer(() -> {
            log.info("Making call to StreamSets to save NEW Entity: {}", weatherEntity);
            return weatherHashRepository.save(getWeatherHash(hashCode));
          }))
          .flatMap(redisHashItem -> transportWeatherDataToStreamSets(weatherEntity));
    };
  }

  @Scheduled(fixedDelayString = "${spring.redis.expiration-in-millis}", initialDelayString = "${spring.redis.expiration-in-millis}")
  public void databaseCallCleanup() {
    webClient.
        delete()
        .uri(streamSetsConfig.getStreamSetsBaseUrl(),
            uri -> uri.path(streamSetsConfig.getStreamSetsCleanupItemPath()).build())
        .header(StreamSetsHeader.X_SDC_APPLICATION_ID, "microservice")
        .retrieve()
        .bodyToMono(String.class)
        .doOnNext(log::info)
        .onErrorContinue((throwable, o) ->
            log.error("Error while processing cleanup {}.Cause: {}", o, throwable.getMessage()))
        .subscribe();
  }

  private WeatherHash getWeatherHash(Integer hashCode) {
    return new WeatherHash().setId(UUID.randomUUID().toString()).setWeatherHashCode(hashCode);
  }

  private Mono<StreamSetsResponse> transportWeatherDataToStreamSets(
      StreamSetsWeatherRequest weatherEntity) {
    final String streamSetsPath =
        weatherEntity.isDuplicate() ? streamSetsConfig.getStreamSetsDuplicateItemPath()
            : streamSetsConfig.getStreamSetsNewItemPath();
    return webClient.
        post()
        .uri(streamSetsConfig.getStreamSetsBaseUrl(),
            uri -> uri.path(streamSetsPath).build())
        .header(StreamSetsHeader.X_SDC_APPLICATION_ID, "microservice")
        .body(BodyInserters.fromValue(weatherEntity))
        .retrieve()
        .bodyToMono(StreamSetsResponse.class)
        .onErrorContinue(
            (throwable, o) -> log.error("Error while processing cleanup {}.Cause: {}", o,
                throwable.getMessage()));
  }
}
