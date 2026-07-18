package com.org.graphql.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record StudentInput(
        @NotBlank String city,
        @NotBlank String street,
        @NotBlank String lastName,
        @NotBlank String firstName,
        @Email @NotBlank String email,
        @NotEmpty List<SubjectInput> subjects
) {
}
