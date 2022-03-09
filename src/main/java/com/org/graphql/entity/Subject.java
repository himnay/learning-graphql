package com.org.graphql.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "marks_obtained")
	private Double marksObtained;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
