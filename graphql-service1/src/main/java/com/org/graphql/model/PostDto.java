package com.org.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private AuthorDto author;
}
