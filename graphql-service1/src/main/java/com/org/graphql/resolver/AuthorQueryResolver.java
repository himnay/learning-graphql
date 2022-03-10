package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.model.AuthorDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    public List<AuthorDto> getAuthors() {
        return List.of(AuthorDto.builder()
                .id(UUID.randomUUID())
                .name("Himansu Nayak")
                .email("himansuxnayak@gmail.com")
                .build());
    }
}
