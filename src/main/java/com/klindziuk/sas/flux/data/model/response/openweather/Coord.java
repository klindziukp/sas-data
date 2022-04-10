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
public class Coord {

  @JsonProperty("lon") private String lon;
  @JsonProperty("lat") private String lat;
}
