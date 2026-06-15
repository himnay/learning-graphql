package com.org.graphql.repository;

import com.org.graphql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.address LEFT JOIN FETCH s.learningSubjects WHERE s.id = :id")
    Optional<Student> findByIdWithDetails(Long id);
}
