package com.klindziuk.sas.flux.data.model.response.streamsets;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class StreamSetsData {

  @JsonProperty("wind_speed")  private Float windSpeed;
  @JsonProperty("description") private String description;
  @JsonProperty("weather")     private String weather;
  @JsonProperty("temperature") private Float temperature;
  @JsonProperty("feels_like")  private Float feelsLike;
  @JsonProperty("city")        private String city;
  @JsonProperty("country")     private String country;
  @JsonProperty("sampleData")  private String sampleData;
}
