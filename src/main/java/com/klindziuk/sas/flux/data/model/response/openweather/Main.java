package com.klindziuk.sas.flux.data.model.response.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Main {

  @JsonProperty("temp")       private Float temp;
  @JsonProperty("temp_min")   private Float tempMin;
  @JsonProperty("temp_max")   private Float tempMax;
  @JsonProperty("feels_like") private Float feelsLike;
  @JsonProperty("grnd_level") private String groundLevel;
  @JsonProperty("humidity")   private String humidity;
  @JsonProperty("pressure")   private String pressure;
  @JsonProperty("sea_level")  private String seaLevel;
}
