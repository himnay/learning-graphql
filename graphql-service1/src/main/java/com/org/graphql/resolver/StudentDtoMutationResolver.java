package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentRequestDto;
import com.org.graphql.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentDtoMutationResolver implements GraphQLMutationResolver {

    private final StudentService studentService;

    public StudentDto createStudent(StudentRequestDto studentRequestDto) {
        var student = studentService.createStudent(studentRequestDto);
        log.info("Student created [ {} ] ", student);
        return new StudentDto(student);
    }

}
