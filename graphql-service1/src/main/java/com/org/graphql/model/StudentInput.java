package com.org.graphql.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record StudentInput(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email,
        @NotBlank String street,
        @NotBlank String city,
        @NotEmpty List<SubjectInput> subjects
) {
}
