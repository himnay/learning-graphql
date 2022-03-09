package com.org.graphql.model;

import com.org.graphql.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class StudentDto {

	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private String street;

	private String city;

	private List<SubjectDto> subjects;

	public StudentDto(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public StudentDto (Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();

		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();

		if (student.getLearningSubjects() != null) {
			subjects = student.getLearningSubjects()
					.stream()
					.map(subject -> new SubjectDto(subject))
					.collect(toList());
		}
	}
}
