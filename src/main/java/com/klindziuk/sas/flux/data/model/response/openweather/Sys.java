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
public class Sys {

  @JsonProperty("id")      private String id;
  @JsonProperty("country") private String country;
  @JsonProperty("sunrise") private String sunrise;
  @JsonProperty("sunset")  private String sunset;
  @JsonProperty("type")    private String type;
}
