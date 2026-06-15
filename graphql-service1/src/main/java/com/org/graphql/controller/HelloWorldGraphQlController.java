package com.org.graphql.controller;

import com.org.graphql.model.HelloWorldInput;
import com.org.graphql.model.MessageDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class HelloWorldGraphQlController {

    @QueryMapping
    public String helloWorld() {
        return "Hello, GraphQL World!";
    }

    @QueryMapping
    public List<String> names() {
        return List.of("Himansu", "Ujwol", "Sunny");
    }

    @QueryMapping
    public MessageDto message() {
        return new MessageDto(UUID.randomUUID().toString(), "Today weather is sunny");
    }

    @QueryMapping
    public String fullName(@Argument String firstName, @Argument String lastName) {
        return firstName + " " + lastName;
    }

    @QueryMapping
    public String fullNameRequestObject(@Argument HelloWorldInput request) {
        return request.firstName() + " " + request.lastName();
    }
}
