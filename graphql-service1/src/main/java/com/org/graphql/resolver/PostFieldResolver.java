package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.org.graphql.model.AuthorDto;
import com.org.graphql.model.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    public AuthorDto getAuthor(PostDto postDto) {
        return AuthorDto.builder()
                .id(postDto.getId())
                .name(postDto.getAuthor().getName())
                .email(postDto.getAuthor().getEmail())
                .build();
    }
}