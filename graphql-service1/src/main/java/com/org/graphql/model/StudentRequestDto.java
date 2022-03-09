package com.org.graphql.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequestDto {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String street;

    private String city;

    private List<SubjectDto> subjects;
}
