package com.org.graphql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "marks_obtained")
    private Double marksObtained;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
