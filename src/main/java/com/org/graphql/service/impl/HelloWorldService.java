package com.org.graphql.service.impl;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.model.HelloWorldDto;
import com.org.graphql.service.HelloWorld;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldService implements HelloWorld, GraphQLQueryResolver {

    @Override
    public String helloWorld() {
        return "helloWorld";
    }

    @Override
    public List<String> names() {
        return List.of("himansu", "ujwol", "sunny");
    }

    @Override
    public String fullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public String fullNameRequestObject(HelloWorldDto request) {
        return request.getFirstName() + " " + request.getLastName();
    }
}
