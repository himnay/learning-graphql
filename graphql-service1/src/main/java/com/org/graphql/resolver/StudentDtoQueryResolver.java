package com.org.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.org.graphql.enums.SubjectEnum;
import com.org.graphql.model.AddressDto;
import com.org.graphql.model.StudentDto;
import com.org.graphql.model.SubjectDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.org.graphql.enums.SubjectEnum.All;
import static java.util.stream.Collectors.toList;

@Component
public class StudentDtoQueryResolver implements GraphQLResolver<StudentDto> {

    //the method name "getSubjects" has to match with field name "subjects" in StudentDto class
    public List<SubjectDto> getSubjects(StudentDto studentDto, SubjectEnum subjectType) {
        return studentDto.getStudent().getLearningSubjects()
                .stream()
                .filter(subject -> subjectType == All || subject.getSubjectName().equals(subjectType.name()))
                .map(subject -> new SubjectDto(subject))
                .collect(toList());
    }

    //the method name "getAddress" has to match with field name "address" in StudentDto class
    public AddressDto getAddress(StudentDto studentDto) {
        var address = studentDto.getStudent().getAddress();
        return new AddressDto(address.getStreet(), address.getCity());
    }

    //the method name "getFullName" has to match with field name "fullName" in StudentDto class
    public String getFullName(StudentDto studentDto) {
        return studentDto.getStudent().getFirstName() + " " + studentDto.getStudent().getLastName();
    }
}
