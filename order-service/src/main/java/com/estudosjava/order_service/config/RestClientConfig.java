package com.estudosjava.order_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.estudosjava.order_service.client.InventoryClient;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
  
  @Value("${inventory.url}")
  private String inventoryServiceUrl;
  private final ObservationRegistry observationRegistry;

  @Bean
public InventoryClient inventoryClient(RestTemplateBuilder restTemplateBuilder) {
    var factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(3000);
    factory.setReadTimeout(3000);

    var restClient = RestClient.builder()
            .baseUrl(inventoryServiceUrl)
            .requestFactory(factory)
            .observationRegistry(observationRegistry)
            .build();

    var adapter = RestClientAdapter.create(restClient);
    var proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
    return proxyFactory.createClient(InventoryClient.class);
  }
}
