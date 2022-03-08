package com.org.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

	@JsonIgnore
	private long id;

	@JsonProperty("first_name")
	private String firstName;

	private String lastName;
}
