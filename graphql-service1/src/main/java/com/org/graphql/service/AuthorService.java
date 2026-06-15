package com.org.graphql.service;

import com.org.graphql.model.AuthorDto;
import com.org.graphql.model.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    private static final List<AuthorDto> AUTHORS = List.of(
            new AuthorDto("1", "Himansu Nayak", "himansu@example.com"),
            new AuthorDto("2", "John Smith", "john@example.com")
    );

    private static final Map<String, List<PostDto>> POSTS_BY_AUTHOR = Map.of(
            "1", List.of(
                    new PostDto("1", "Learning GraphQL", "Deep dive into Spring for GraphQL", "Technology", "1"),
                    new PostDto("2", "Spring Boot 4.1", "What is new in Spring Boot 4.1", "Technology", "1")
            ),
            "2", List.of(
                    new PostDto("3", "Java 25 Features", "Virtual threads, records, pattern matching", "Java", "2")
            )
    );

    public List<AuthorDto> getAllAuthors() {
        return AUTHORS;
    }

    public List<PostDto> getPostsByAuthorId(String authorId) {
        return POSTS_BY_AUTHOR.getOrDefault(authorId, List.of());
    }

    public AuthorDto getAuthorById(String authorId) {
        return AUTHORS.stream()
                .filter(a -> a.id().equals(authorId))
                .findFirst()
                .orElse(null);
    }
}
