package com.klindziuk.sas.flux.data.config;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Configuration
public class OpenWeatherConfig {

  @Value("${open-weather.url.base}")   private String openWeatherUrl;
  @Value("${open-weather.app.id}")     private String applicationId;
  @Value("${open-weather.app.cities}") private List<String> cities;

  public MultiValueMap<String, String> getMultiValueRequestMap() {
    final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.add("q", getRandomCity());
    multiValueMap.add("appId", applicationId);
    return multiValueMap;
  }

  private String getRandomCity() {
    final int randomCityIndex = new Random().nextInt(cities.size());
    return Objects.requireNonNull(cities.get(randomCityIndex));
  }
}
