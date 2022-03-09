package com.org.graphql.model;

import com.org.graphql.entity.Subject;
import lombok.Data;

@Data
public class SubjectDto {

	private Long id;

	private String subjectName;

	private Double marksObtained;

	public SubjectDto(Subject subject) {
		this.id = subject.getId();
		this.subjectName = subject.getSubjectName();
		this.marksObtained = subject.getMarksObtained();
	}
}
