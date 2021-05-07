package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Grades;
@Repository
public interface GradeRepository extends JpaRepository<Grades, Long> {

	List<Grades> findBySubjectid(long id);
	Grades findBySubjectId(long id);
}
