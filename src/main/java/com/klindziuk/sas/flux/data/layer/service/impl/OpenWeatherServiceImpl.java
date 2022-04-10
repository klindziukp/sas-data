package com.klindziuk.sas.flux.data.layer.service.impl;

import com.klindziuk.sas.flux.data.config.OpenWeatherConfig;
import com.klindziuk.sas.flux.data.layer.service.OpenWeatherService;
import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

  private final OpenWeatherConfig openWeatherConfig;
  private final WebClient webClient;

  public OpenWeatherServiceImpl(OpenWeatherConfig openWeatherConfig, WebClient webClient) {
    this.openWeatherConfig = openWeatherConfig;
    this.webClient = webClient;
  }

  @Override
  public Flux<OpenWeatherResponse> produceOpenWeatherFlux() {
    return webClient.
        get()
        .uri(openWeatherConfig.getOpenWeatherUrl(),
            uri -> uri.queryParams(openWeatherConfig.getMultiValueRequestMap())
                .build())
        .retrieve()
        .bodyToFlux(OpenWeatherResponse.class);
  }
}
