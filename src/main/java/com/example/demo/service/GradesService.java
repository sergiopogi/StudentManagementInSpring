package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Grades;
import com.example.demo.repositories.GradeRepository;

@Service
public class GradesService {

	@Autowired
	private GradeRepository gradeRepository;
	
	public List<Grades> findGrades(long id) {
		return gradeRepository.findBySubjectid(id);
	}
	public void saveGrades(Grades grades) {
		gradeRepository.save(grades);
	}
	
	public Grades findGradesBySubjectId(long subjectId) {
		return gradeRepository.findBySubjectId(subjectId);
	}
}
