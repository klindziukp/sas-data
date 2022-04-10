package com.klindziuk.sas.flux.data.model.response.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class OpenWeatherResponse {

  @JsonProperty("id")         private String id;
  @JsonProperty("visibility") private String visibility;
  @JsonProperty("timezone")   private String timezone;
  @JsonProperty("main")       private Main main;
  @JsonProperty("clouds")     private Clouds clouds;
  @JsonProperty("sys")        private Sys sys;
  @JsonProperty("dt")         private String dt;
  @JsonProperty("coord")      private Coord coord;
  @JsonProperty("weather")    private List<Weather> weather;
  @JsonProperty("name")       private String name;
  @JsonProperty("cod")        private String cod;
  @JsonProperty("base")       private String base;
  @JsonProperty("wind")       private Wind wind;
}
