package com.alasdeplata.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class WebClientConfig {

    @Value("${ollama.base-url}")
    private String baseUrl;

    @Bean
    @Qualifier("ollamaClient")
    public WebClient ollamaClient() {
        return WebClient.builder()
                .baseUrl("http://ollama:11434")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean
    @Qualifier("backendClient")
    public WebClient backendClient() {
        return WebClient.builder()
                .baseUrl("http://springboot-app:8080") // nombre del contenedor en red Docker
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
