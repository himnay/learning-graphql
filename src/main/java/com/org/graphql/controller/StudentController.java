package com.org.graphql.controller;

import com.org.graphql.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@GetMapping
	public Student getStudent () {
		return new Student(1, "John", "Smith");
	}
}