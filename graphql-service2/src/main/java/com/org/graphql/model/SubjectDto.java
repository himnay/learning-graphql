package com.org.graphql.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDto {

	private Long id;

	private String subjectName;

	private Double marksObtained;
}
