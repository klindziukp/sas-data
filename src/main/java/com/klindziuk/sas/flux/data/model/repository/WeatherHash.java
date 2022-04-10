package com.klindziuk.sas.flux.data.model.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeatherHash {

  @Id
  private String id;
  private Integer weatherHashCode;
}
