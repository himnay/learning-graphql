package com.org.graphql.service.impl;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.entity.Student;
import com.org.graphql.model.StudentDto;
import com.org.graphql.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements GraphQLQueryResolver {

    private final StudentRepository studentRepository;

    public StudentDto getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return new StudentDto(student.get());
        }
        return new StudentDto();
    }

}
