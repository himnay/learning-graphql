package com.org.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
class GraphqlService1Application {

    /** Application entry point. */
    public static void main(String[] args) {
        SpringApplication.run(GraphqlService1Application.class, args);
    }
}
