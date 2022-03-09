package com.org.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDto {

    private long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private String street;

    private String city;

    private List<SubjectDto> subjects;
}
