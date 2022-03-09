package com.org.graphql.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentDto {

	private long id;

	private String firstName;

	private String lastName;

	private String fullName;

	private String email;

	private AddressDto address;

	private List<SubjectDto> subjects;
}
