package com.org.graphql.mapper;

import com.org.graphql.entity.Address;
import com.org.graphql.entity.Student;
import com.org.graphql.entity.Subject;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.SubjectDto;

public final class StudentMapper {

    private StudentMapper() {}

    /** Converts this object to dto. */
    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    /** Converts this object to subject dto. */
    public static SubjectDto toSubjectDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getSubjectName(), subject.getMarksObtained());
    }

    /** Converts this object to address dto. */
    public static AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getStreet(), address.getCity());
    }
}
