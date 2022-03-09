package com.org.graphql.service;

import com.org.graphql.entity.Student;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentRequestDto;

public interface StudentService {

    StudentDto getStudent(long id);

    Student createStudent (StudentRequestDto studentRequestDto);
}
