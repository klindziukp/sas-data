package com.klindziuk.sas.flux.data.model.response.streamsets;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@Accessors(chain = true)
public class StreamSetsResponse {

  @JsonProperty("httpStatusCode") private String httpStatusCode;
  @JsonProperty("data")           private List<StreamSetsData> streamSetsData;
  @JsonProperty("errorMessage")   private String errorMessage;
  @JsonProperty("error")          private List<String> error;
}
