package com.klindziuk.sas.flux.data.config;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

  @Bean(name = "webclient")
  public WebClient webclient() {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient()))
        .build();
  }

  private HttpClient httpClient() {
    final int timeout = 1000;
    return HttpClient.create()
        .doOnConnected(connection -> {
          connection.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
          connection.addHandlerLast(new WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
        });
  }
}
