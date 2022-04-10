package com.klindziuk.sas.flux.data.layer.controller;

import com.klindziuk.sas.flux.data.model.response.streamsets.StreamSetsResponse;
import com.klindziuk.sas.flux.data.layer.service.OpenWeatherService;
import com.klindziuk.sas.flux.data.layer.service.StreamSetsService;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class WeatherController {

  private final OpenWeatherService openWeatherService;
  private final StreamSetsService streamSetsService;

  @Autowired
  public WeatherController(OpenWeatherService openWeatherService,
      StreamSetsService streamSetsService) {
    this.openWeatherService = openWeatherService;
    this.streamSetsService = streamSetsService;
  }

  @SuppressWarnings("deprecation")
  @GetMapping(value = "/start-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<StreamSetsResponse> streamWeatherRequestsStreamSetResponses() {
    return Flux.interval(Duration.ofSeconds(10)).flatMap(m ->
        openWeatherService.produceOpenWeatherFlux()
            .onErrorContinue(
                (throwable, o) -> log.error("Error while processing cleanup {}.Cause: {}", o,
                    throwable.getMessage()))
            .flatMap(streamSetsService.provideStreamSetsResponse()));
  }
}