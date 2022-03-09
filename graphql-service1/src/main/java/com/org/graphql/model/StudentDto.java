package com.org.graphql.model;

import com.org.graphql.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentDto {

	private long id;

	private String firstName;

	private String lastName;

	// doesn't exist in db, created by resolver
	private String fullName;

	private String email;

	//define a resolver
	private AddressDto address;

	// placeholder to db entity used by resolver
	private Student student;

	//define a resolver
	private List<SubjectDto> subjects;

	public StudentDto(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public StudentDto (Student student) {
		// hold the reference
		this.student = student;

		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
	}
}
