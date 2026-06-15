package com.org.graphql.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class StudentGraphQlControllerIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("graphql_db")
            .withUsername("graphql")
            .withPassword("graphql");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    int port;

    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> graphql(String query) {
        return restClient.post()
                .uri("/graphql")
                .body(Map.of("query", query))
                .retrieve()
                .body(Map.class);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> graphqlWithVariables(String query, Map<String, Object> variables) {
        return restClient.post()
                .uri("/graphql")
                .body(Map.of("query", query, "variables", variables))
                .retrieve()
                .body(Map.class);
    }

    @Test
    void getStudent_returnsBasicFields() {
        var result = graphql("{ getStudent(id: \"1\") { id firstName lastName email } }");
        @SuppressWarnings("unchecked")
        var student = (Map<String, Object>) ((Map<String, Object>) result.get("data")).get("getStudent");
        assertThat(student.get("firstName")).isEqualTo("John");
        assertThat(student.get("lastName")).isEqualTo("Smith");
    }

    @Test
    void getStudent_returnsFullNameViaFieldResolver() {
        var result = graphql("{ getStudent(id: \"1\") { fullName } }");
        @SuppressWarnings("unchecked")
        var student = (Map<String, Object>) ((Map<String, Object>) result.get("data")).get("getStudent");
        assertThat(student.get("fullName")).isEqualTo("John Smith");
    }

    @Test
    void getStudent_returnsAddressViaFieldResolver() {
        var result = graphql("{ getStudent(id: \"1\") { address { street city } } }");
        @SuppressWarnings("unchecked")
        var student = (Map<String, Object>) ((Map<String, Object>) result.get("data")).get("getStudent");
        @SuppressWarnings("unchecked")
        var address = (Map<String, Object>) student.get("address");
        assertThat(address.get("city")).isEqualTo("Delhi");
    }

    @Test
    void getStudent_returnsSubjectsFilteredByJava() {
        var result = graphql("{ getStudent(id: \"1\") { subjects(subjectType: Java) { subjectName } } }");
        @SuppressWarnings("unchecked")
        var student = (Map<String, Object>) ((Map<String, Object>) result.get("data")).get("getStudent");
        @SuppressWarnings("unchecked")
        var subjects = (List<Map<String, Object>>) student.get("subjects");
        assertThat(subjects).hasSize(1);
        assertThat(subjects.getFirst().get("subjectName")).isEqualTo("Java");
    }

    @Test
    void createStudent_persistsAndReturns() {
        String mutation = """
                mutation CreateStudent($student: StudentInput!) {
                    createStudent(student: $student) { id firstName lastName email }
                }
                """;
        var variables = Map.<String, Object>of("student", Map.of(
                "firstName", "Alice",
                "lastName", "Walker",
                "email", "alice@test.com",
                "street", "Baker St",
                "city", "London",
                "subjects", List.of(Map.of("subjectName", "Java", "marksObtained", 95.0))
        ));
        var result = graphqlWithVariables(mutation, variables);
        @SuppressWarnings("unchecked")
        var student = (Map<String, Object>) ((Map<String, Object>) result.get("data")).get("createStudent");
        assertThat(student.get("firstName")).isEqualTo("Alice");
        assertThat(student.get("id")).isNotNull();
    }

    @Test
    void getStudent_notFound_returnsError() {
        var result = graphql("{ getStudent(id: \"9999\") { firstName } }");
        assertThat(result.get("errors")).isNotNull();
    }

    @Test
    void helloWorld_returnsGreeting() {
        var result = graphql("{ helloWorld }");
        @SuppressWarnings("unchecked")
        var data = (Map<String, Object>) result.get("data");
        assertThat(data.get("helloWorld")).isEqualTo("Hello, GraphQL World!");
    }

    @Test
    void authors_returnsAuthorList() {
        var result = graphql("{ authors { id name email } }");
        @SuppressWarnings("unchecked")
        var data = (Map<String, Object>) result.get("data");
        @SuppressWarnings("unchecked")
        var authors = (List<?>) data.get("authors");
        assertThat(authors).isNotEmpty();
    }
}
