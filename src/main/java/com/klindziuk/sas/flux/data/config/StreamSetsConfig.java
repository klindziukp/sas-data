package com.klindziuk.sas.flux.data.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class StreamSetsConfig {

  @Value("${stream-sets.url.base}")                     private String streamSetsBaseUrl;
  @Value("${stream-sets.path.new-item}")                private String streamSetsNewItemPath;
  @Value("${stream-sets.path.duplicate-item}")          private String streamSetsDuplicateItemPath;
  @Value("${stream-sets.path.cleanup-duplicate-items}") private String streamSetsCleanupItemPath;
}
