package com.alasdeplata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://host.docker.internal:11434")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
