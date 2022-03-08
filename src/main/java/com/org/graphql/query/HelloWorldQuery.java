package com.org.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.model.HelloWorld;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelloWorldQuery implements GraphQLQueryResolver {

    public String helloWorld() {
        return "helloWorld";
    }

    public List<String> names() {
        return List.of("himansu", "ujwol", "sunny");
    }

    public String fullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public String fullNameRequestObject(HelloWorld request) {
        return request.getFirstName() + " " + request.getLastName();
    }
}
