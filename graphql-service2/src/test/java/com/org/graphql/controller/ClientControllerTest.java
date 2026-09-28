package com.org.graphql.controller;

import com.org.graphql.client.StudentClient;
import org.junit.jupiter.api.Test;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.ResponseError;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** GraphQL errors relayed by graphql-service1 map to HTTP statuses instead of a blanket 500. */
class ClientControllerTest {

    private final ClientController controller = new ClientController(mock(StudentClient.class));

    @Test
    void notFoundClassificationBecomes404() {
        ProblemDetail problem = controller.onGraphQlError(fieldAccessFailure("NOT_FOUND", "Student not found with id: 9999"));

        assertThat(problem.getStatus()).isEqualTo(404);
        assertThat(problem.getDetail()).isEqualTo("Student not found with id: 9999");
    }

    @Test
    void badRequestClassificationBecomes400() {
        assertThat(controller.onGraphQlError(fieldAccessFailure("BAD_REQUEST", "invalid")).getStatus()).isEqualTo(400);
    }

    @Test
    void anyOtherServerErrorBecomes502() {
        assertThat(controller.onGraphQlError(fieldAccessFailure("INTERNAL_ERROR", "boom")).getStatus()).isEqualTo(502);
    }

    private static FieldAccessException fieldAccessFailure(String classification, String message) {
        ResponseError error = mock(ResponseError.class);
        when(error.getExtensions()).thenReturn(Map.of("classification", classification));
        when(error.getMessage()).thenReturn(message);
        ClientGraphQlResponse response = mock(ClientGraphQlResponse.class);
        when(response.getErrors()).thenReturn(List.of(error));
        FieldAccessException ex = mock(FieldAccessException.class);
        when(ex.getResponse()).thenReturn(response);
        return ex;
    }
}
