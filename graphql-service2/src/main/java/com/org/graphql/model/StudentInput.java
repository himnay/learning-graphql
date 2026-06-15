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
public class StudentInput {
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private List<SubjectInput> subjects;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubjectInput {
        private String subjectName;
        private Double marksObtained;
    }
}
