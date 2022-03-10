package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.org.graphql.model.AuthorDto;
import com.org.graphql.model.PostDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto> {

    public List<PostDto> getPosts(AuthorDto authorDto) {
        return List.of(PostDto.builder()
                .id(UUID.randomUUID())
                .title("Learning GraphQL")
                .description("Continuous Learning")
                .category("Technology")
                .author(authorDto)
                .build());
    }
}