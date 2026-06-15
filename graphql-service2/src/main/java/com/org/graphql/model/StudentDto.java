package com.org.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private AddressDto address;
    private List<SubjectDto> subjects;
}
