package com.org.graphql.service.impl;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.entity.Address;
import com.org.graphql.entity.Student;
import com.org.graphql.entity.Subject;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentRequestDto;
import com.org.graphql.repository.AddressRepository;
import com.org.graphql.repository.StudentRepository;
import com.org.graphql.repository.SubjectRepository;
import com.org.graphql.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService, GraphQLQueryResolver {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final SubjectRepository subjectRepository;

    public StudentDto getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return new StudentDto(student.get());
        }
        return new StudentDto();
    }

    public Student createStudent (StudentRequestDto studentDto) {
        Address address = new Address(studentDto.getStreet(), studentDto.getCity());
        var savedAddress = addressRepository.save(address);

        Student student = new Student(studentDto);
        student.setAddress(savedAddress);
        var savedStudent = studentRepository.save(student);

        var subjects = studentDto.getSubjects()
                .stream()
                .map(subject -> new Subject(subject.getSubjectName(), subject.getMarksObtained(), savedStudent))
                .collect(toList());

        var savedSubjects = subjectRepository.saveAll(subjects);

        savedStudent.setLearningSubjects(savedSubjects);
        return savedStudent;
    }

}
