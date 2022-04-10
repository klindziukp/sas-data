package com.klindziuk.sas.flux.data.layer.repository.impl;

import com.klindziuk.sas.flux.data.config.RedisConfig;
import com.klindziuk.sas.flux.data.layer.repository.WeatherHashRedisRepository;
import com.klindziuk.sas.flux.data.model.repository.WeatherHash;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class WeatherHashRedisRepositoryImpl implements WeatherHashRedisRepository {

  private final RedisConfig redisConfig;
  private final ReactiveRedisOperations<String, WeatherHash> reactiveRedisOperations;

  public WeatherHashRedisRepositoryImpl(RedisConfig redisConfig,
      ReactiveRedisOperations<String, WeatherHash> reactiveRedisOperations) {
    this.redisConfig = redisConfig;
    this.reactiveRedisOperations = reactiveRedisOperations;
    this.reactiveRedisOperations.expire(redisConfig.getKey(),
        Duration.ofMillis(redisConfig.getStoringDuration()));
  }

  public Flux<WeatherHash> findAll() {
    return reactiveRedisOperations.<String, WeatherHash>opsForHash().values(redisConfig.getKey());
  }

  public Mono<WeatherHash> findByHash(Integer weatherHashCode) {
    return this.findAll()
        .filter(w -> weatherHashCode.equals(w.getWeatherHashCode())).last()
        .onErrorResume(NoSuchElementException.class, error -> Mono.empty());
  }

  public Mono<WeatherHash> save(WeatherHash weatherHash) {
    return reactiveRedisOperations.<String, WeatherHash>opsForHash()
        .put(redisConfig.getKey(), weatherHash.getId(), weatherHash)
        .map(w -> weatherHash);
  }

  public Mono<Boolean> deleteAll() {
    return this.reactiveRedisOperations.opsForList().delete(redisConfig.getKey());
  }
}