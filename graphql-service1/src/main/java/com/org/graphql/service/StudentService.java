package com.org.graphql.service;

import com.org.graphql.enums.SubjectEnum;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import com.org.graphql.model.SubjectDto;

import java.util.List;

public interface StudentService {

    StudentDto getStudent(Long id);

    StudentDto createStudent(StudentInput input);

    AddressDto getAddress(Long studentId);

    List<SubjectDto> getSubjects(Long studentId, SubjectEnum subjectType);
}
