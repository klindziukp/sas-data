package com.klindziuk.sas.flux.data.layer.service;

import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import reactor.core.publisher.Flux;

public interface OpenWeatherService {

  Flux<OpenWeatherResponse> produceOpenWeatherFlux();

}
