package com.org.graphql.controller;

import com.org.graphql.client.StudentClient;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @PostMapping
    public Mono<StudentDto> createStudent(@RequestBody StudentInput input) {
        return studentClient.createStudent(input);
    }
}
