package com.org.graphql.service.impl;

import com.org.graphql.entity.Address;
import com.org.graphql.entity.Student;
import com.org.graphql.entity.Subject;
import com.org.graphql.enums.SubjectEnum;
import com.org.graphql.exception.StudentNotFoundException;
import com.org.graphql.mapper.StudentMapper;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import com.org.graphql.model.SubjectDto;
import com.org.graphql.repository.AddressRepository;
import com.org.graphql.repository.StudentRepository;
import com.org.graphql.repository.SubjectRepository;
import com.org.graphql.service.StudentService;
import com.org.graphql.strategy.SubjectFilterStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudent(Long id) {
        return studentRepository.findByIdWithDetails(id)
                .map(StudentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    @Transactional
    public StudentDto createStudent(StudentInput input) {
        Address address = addressRepository.save(
                Address.builder()
                        .street(input.street())
                        .city(input.city())
                        .build()
        );

        Student student = studentRepository.save(
                Student.builder()
                        .firstName(input.firstName())
                        .lastName(input.lastName())
                        .email(input.email())
                        .address(address)
                        .build()
        );

        List<Subject> subjects = input.subjects().stream()
                .map(s -> Subject.builder()
                        .subjectName(s.subjectName())
                        .marksObtained(s.marksObtained())
                        .student(student)
                        .build())
                .toList();
        subjectRepository.saveAll(subjects);
        student.setLearningSubjects(subjects);

        log.info("Created student [id={}, name={} {}]", student.getId(), student.getFirstName(), student.getLastName());
        return StudentMapper.toDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressDto getAddress(Long studentId) {
        return studentRepository.findByIdWithDetails(studentId)
                .map(s -> StudentMapper.toAddressDto(s.getAddress()))
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDto> getSubjects(Long studentId, SubjectEnum subjectType) {
        SubjectFilterStrategy strategy = SubjectFilterStrategy.of(subjectType);

        return studentRepository.findByIdWithDetails(studentId)
                .map(s -> s.getLearningSubjects().stream()
                        .filter(strategy::test)
                        .map(StudentMapper::toSubjectDto)
                        .toList())
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }
}
