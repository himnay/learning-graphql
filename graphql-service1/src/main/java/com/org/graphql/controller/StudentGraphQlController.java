package com.org.graphql.controller;

import com.org.graphql.enums.SubjectEnum;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import com.org.graphql.model.SubjectDto;
import com.org.graphql.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentGraphQlController {

    private final StudentService studentService;

    @QueryMapping
    public StudentDto getStudent(@Argument Long id) {
        log.info("GraphQL query: getStudent [id={}]", id);
        return studentService.getStudent(id);
    }

    @MutationMapping
    public StudentDto createStudent(@Argument StudentInput student) {
        log.info("GraphQL mutation: createStudent [firstName={}]", student.firstName());
        return studentService.createStudent(student);
    }

    @SchemaMapping(typeName = "StudentDto", field = "fullName")
    public String fullName(StudentDto student) {
        return student.firstName() + " " + student.lastName();
    }

    @SchemaMapping(typeName = "StudentDto", field = "address")
    public AddressDto address(StudentDto student) {
        return studentService.getAddress(student.id());
    }

    @SchemaMapping(typeName = "StudentDto", field = "subjects")
    public List<SubjectDto> subjects(StudentDto student, @Argument SubjectEnum subjectType) {
        return studentService.getSubjects(student.id(), subjectType);
    }
}
