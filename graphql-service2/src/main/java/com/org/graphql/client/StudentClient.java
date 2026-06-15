package com.org.graphql.client;

import com.org.graphql.model.StudentDto;
import com.org.graphql.model.StudentInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentClient {

    private final HttpGraphQlClient graphQlClient;

    public Mono<StudentDto> getStudent(Integer id) {
        log.info("Fetching student via GraphQL client [id={}]", id);
        return graphQlClient
                .document("""
                        query GetStudent($id: ID!) {
                            getStudent(id: $id) {
                                id firstName lastName email
                                address { street city }
                                subjects(subjectType: All) { id subjectName marksObtained }
                            }
                        }
                        """)
                .variable("id", id.toString())
                .retrieve("getStudent")
                .toEntity(StudentDto.class);
    }

    public Mono<StudentDto> getStudentWithSubjectFilter(Integer id, String subjectType) {
        log.info("Fetching student with filter via GraphQL client [id={}, filter={}]", id, subjectType);
        return graphQlClient
                .document("""
                        query GetStudentFiltered($id: ID!, $subType: SubjectEnum) {
                            getStudent(id: $id) {
                                id firstName lastName fullName email
                                address { street city }
                                subjects(subjectType: $subType) { id subjectName marksObtained }
                            }
                        }
                        """)
                .variable("id", id.toString())
                .variable("subType", subjectType)
                .retrieve("getStudent")
                .toEntity(StudentDto.class);
    }

    public Mono<StudentDto> createStudent(StudentInput input) {
        log.info("Creating student via GraphQL client [firstName={}]", input.getFirstName());
        return graphQlClient
                .document("""
                        mutation CreateStudent($student: StudentInput!) {
                            createStudent(student: $student) {
                                id firstName lastName fullName email
                                address { street city }
                                subjects(subjectType: All) { subjectName marksObtained }
                            }
                        }
                        """)
                .variable("student", input)
                .retrieve("createStudent")
                .toEntity(StudentDto.class);
    }
}
