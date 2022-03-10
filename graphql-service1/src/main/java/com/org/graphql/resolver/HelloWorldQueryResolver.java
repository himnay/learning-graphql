package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.model.HelloWorldDto;
import com.org.graphql.model.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld() {
        return "helloWorld";
    }

    public List<String> names() {
        return List.of("Himansu", "Ujwol", "Sunny");
    }

    //the method name "getMessage" has to match with field name "message" in schema file
    public MessageDto getMessage() {
        return new MessageDto(UUID.randomUUID(), "today weather is sunny");
    }

    public String fullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public String fullNameRequestObject(HelloWorldDto request) {
        return request.getFirstName() + " " + request.getLastName();
    }
}
