package com.klindziuk.sas.flux.data.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@RedisHash("weathers")
public class StreamSetsWeatherRequest {

  @Id
  @JsonProperty("wind_speed")  private Float windSpeed;
  @JsonProperty("description") private String description;
  @JsonProperty("weather")     private String weather;
  @JsonProperty("temperature") private Float temperature;
  @JsonProperty("feels_like")  private Float feelsLike;
  @JsonProperty("city")        private String city;
  @JsonProperty("country")     private String country;

  @JsonIgnore                  private boolean duplicate = false;
}
