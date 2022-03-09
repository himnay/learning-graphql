package com.org.graphql.controller;

import com.org.graphql.model.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@GetMapping
	public StudentDto getStudent () {
		return new StudentDto(1, "John", "Smith");
	}
}