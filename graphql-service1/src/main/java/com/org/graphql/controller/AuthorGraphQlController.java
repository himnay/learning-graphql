package com.org.graphql.controller;

import com.org.graphql.model.AuthorDto;
import com.org.graphql.model.PostDto;
import com.org.graphql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorGraphQlController {

    private final AuthorService authorService;

    @QueryMapping
    public List<AuthorDto> authors() {
        return authorService.getAllAuthors();
    }

    @SchemaMapping(typeName = "Author", field = "posts")
    public List<PostDto> posts(AuthorDto author) {
        return authorService.getPostsByAuthorId(author.id());
    }

    @SchemaMapping(typeName = "Post", field = "author")
    public AuthorDto author(PostDto post) {
        return authorService.getAuthorById(post.authorId());
    }
}
