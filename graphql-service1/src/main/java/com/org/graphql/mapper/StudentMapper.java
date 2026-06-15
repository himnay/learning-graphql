package com.org.graphql.mapper;

import com.org.graphql.entity.Address;
import com.org.graphql.entity.Student;
import com.org.graphql.entity.Subject;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.SubjectDto;

public final class StudentMapper {

    private StudentMapper() {}

    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public static SubjectDto toSubjectDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getSubjectName(), subject.getMarksObtained());
    }

    public static AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getStreet(), address.getCity());
    }
}
