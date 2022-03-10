package com.org.graphql.controller;

import com.org.graphql.client.StudentClient;
import com.org.graphql.model.StudentCreateDto;
import com.org.graphql.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class ClientController {
	
	private final StudentClient studentClient;

	@GetMapping("/{id}")
	public StudentDto getStudent(@PathVariable Integer id) throws IOException {
		return studentClient.getStudent(id);
	}

	@GetMapping("/var/{id}")
	public StudentDto getStudentUsingVariable(@PathVariable Integer id) throws IOException {
		return studentClient.getStudentWithGraphQLVariable(id);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StudentDto createStudent(@RequestBody StudentCreateDto studentDto) throws IOException {
		return studentClient.createStudent(studentDto);
	}
	
}
