package com.org.graphql.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record SubjectInput(
        @NotBlank String subjectName,
        @PositiveOrZero Double marksObtained
) {
}
