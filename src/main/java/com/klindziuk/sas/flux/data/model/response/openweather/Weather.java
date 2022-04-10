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
public class Weather {

  @JsonProperty("id")          private String id;
  @JsonProperty("icon")        private String icon;
  @JsonProperty("description") private String description;
  @JsonProperty("main")        private String main;
}
