package com.org.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Table(name = "post")
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String title;

    private String description;

    private String category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
