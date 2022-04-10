package com.klindziuk.sas.flux.data.layer.service;

import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import com.klindziuk.sas.flux.data.model.response.streamsets.StreamSetsResponse;
import java.util.function.Function;
import reactor.core.publisher.Mono;

public interface StreamSetsService {

  Function<OpenWeatherResponse, Mono<StreamSetsResponse>> provideStreamSetsResponse();
}
