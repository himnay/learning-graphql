package com.org.graphql.config;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class GraphQLClientConfig {

    @Value("${graphql.server.url:http://localhost:8080/graphql}")
    private String graphQlServerUrl;

    /** Defines the graph ql client bean. */
    @Bean
    public HttpGraphQlClient graphQlClient() {
        // Bounded timeouts: without them a stalled graphql-service1 would hold every request forever.
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
                .responseTimeout(Duration.ofSeconds(10));
        WebClient webClient = WebClient.builder()
                .baseUrl(graphQlServerUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("Content-Type", "application/json")
                .build();
        return HttpGraphQlClient.create(webClient);
    }
}
