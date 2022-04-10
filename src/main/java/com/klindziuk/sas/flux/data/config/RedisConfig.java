package com.klindziuk.sas.flux.data.config;

import com.klindziuk.sas.flux.data.model.repository.WeatherHash;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Data
@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")                 private String redisHost;
  @Value("${spring.redis.port}")                 private int redisPort;
  @Value("${spring.redis.key}")                  private String key;
  @Value("${spring.redis.expiration-in-millis}") private int storingDuration;

  @Bean
  @SuppressWarnings({"unchecked", "rawtypes"})
  public ReactiveRedisTemplate<String, WeatherHash> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory factory) {
    return new ReactiveRedisTemplate<String, WeatherHash>(
        factory,
        RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(WeatherHash.class))
    );
  }
}
