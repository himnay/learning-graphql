package com.org.graphql.controller;

import com.org.graphql.client.StudentClient;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class ClientController {

    private final StudentClient studentClient;

    @GetMapping("/{id}")
    public Mono<StudentDto> getStudent(@PathVariable Integer id) {
        return studentClient.getStudent(id);
    }

    @GetMapping("/{id}/filter")
    public Mono<StudentDto> getStudentWithFilter(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "All") String subjectType) {
        return studentClient.getStudentWithSubjectFilter(id, subjectType);
    }

    /** Creates student. */
    @PostMapping
    public Mono<StudentDto> createStudent(@RequestBody StudentInput input) {
        return studentClient.createStudent(input);
    }

    /**
     * A GraphQL error from graphql-service1 (the field came back null with "errors") surfaces here
     * as {@link FieldAccessException}. Map its classification to the matching HTTP status instead
     * of a blanket 500: NOT_FOUND → 404, BAD_REQUEST → 400, anything else → 502 Bad Gateway.
     */
    @ExceptionHandler(FieldAccessException.class)
    public ProblemDetail onGraphQlError(FieldAccessException ex) {
        List<ResponseError> errors = ex.getResponse().getErrors();
        String classification = errors.isEmpty() ? ""
                : String.valueOf(errors.getFirst().getExtensions().get("classification"));
        HttpStatus status = switch (classification) {
            case "NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "BAD_REQUEST" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_GATEWAY;
        };
        String detail = errors.isEmpty() ? ex.getMessage() : errors.getFirst().getMessage();
        return ProblemDetail.forStatusAndDetail(status, detail);
    }
}
