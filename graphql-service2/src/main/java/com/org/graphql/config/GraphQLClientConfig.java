package com.org.graphql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

    @Value("${graphql.server.url:http://localhost:8080/graphql}")
    private String graphQlServerUrl;

    @Bean
    public HttpGraphQlClient graphQlClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(graphQlServerUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
        return HttpGraphQlClient.create(webClient);
    }
}
