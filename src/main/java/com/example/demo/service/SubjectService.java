package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Subjects;
import com.example.demo.repositories.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public void addSubject (Subjects subject) {
		subjectRepository.save(subject);
	}
	public List<Subjects> findSubjectsByStudentId(long studentId) {
		return subjectRepository.findByStudentId(studentId);
	}
	public Subjects findSubjectById(long id) {
		return subjectRepository.findById(id);
	}
}
