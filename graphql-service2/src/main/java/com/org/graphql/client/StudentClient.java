package com.org.graphql.client;

import com.org.graphql.model.StudentCreateDto;
import com.org.graphql.model.StudentDto;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentClient {

	private final GraphQLWebClient graphQLWebClient;

	public StudentDto getStudent (Integer id) throws IOException {
		String graphqlQuery = Files.readString(ResourceUtils.getFile(this.getClass().getResource("/query/student.txt")).toPath());
		GraphQLRequest request = GraphQLRequest.builder()
				.query(graphqlQuery)
				.build();

		GraphQLResponse response = graphQLWebClient.post(request).block();
		if(response.getErrors().size() > 0) {
			response.getErrors().forEach(graphQLError -> log.error("Error from the service : [ {} }", graphQLError.getMessage()));
		}		StudentDto student = response.get("getStudent", StudentDto.class);
		return student;
	}

	public StudentDto getStudentWithGraphQLVariable (Integer id) throws IOException {
		String graphqlQuery = Files.readString(ResourceUtils.getFile(this.getClass().getResource("/query/student_variable.txt")).toPath());
		Map<String, Object> variables = new HashMap<>();
		variables.put("id", id);
		variables.put("subType", "All");

		GraphQLRequest request = GraphQLRequest.builder()
				.query(graphqlQuery)
				.variables(variables)
				.build();

		GraphQLResponse response = graphQLWebClient.post(request).block();
		if(response.getErrors().size() > 0) {
			response.getErrors().forEach(graphQLError -> log.error("Error from the service : [ {} }", graphQLError.getMessage()));
		}		StudentDto student = response.get("getStudent", StudentDto.class);
		return student;
	}

	public StudentDto createStudent(StudentCreateDto studentDto) throws IOException {
		String graphqlQuery = Files.readString(ResourceUtils.getFile(this.getClass().getResource("/mutation/student_create.txt")).toPath());
		Map<String, Object> variables = new HashMap<>();
		variables.put("student", studentDto);

		GraphQLRequest request = GraphQLRequest.builder()
				.query(graphqlQuery)
				.variables(variables)
				.build();

		GraphQLResponse response = graphQLWebClient.post(request).block();
		if(response.getErrors().size() > 0) {
			response.getErrors().forEach(graphQLError -> log.error("Error from the service : [ {} }", graphQLError.getMessage()));
		}
		StudentDto student = response.get("createStudent", StudentDto.class);
		return student;
	}
}
