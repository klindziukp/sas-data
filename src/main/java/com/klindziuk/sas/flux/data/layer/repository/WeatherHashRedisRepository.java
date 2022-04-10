package com.klindziuk.sas.flux.data.layer.repository;

import com.klindziuk.sas.flux.data.model.repository.WeatherHash;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeatherHashRedisRepository {

   Flux<WeatherHash> findAll();
   Mono<WeatherHash> findByHash(Integer weatherHashCode);
   Mono<WeatherHash> save(WeatherHash weatherHash);
   Mono<Boolean> deleteAll();
}
