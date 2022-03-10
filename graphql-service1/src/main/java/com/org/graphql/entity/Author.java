package com.org.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "author")
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts;

}
