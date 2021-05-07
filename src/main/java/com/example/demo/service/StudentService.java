package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Grades;
import com.example.demo.entities.Students;
import com.example.demo.entities.Subjects;
import com.example.demo.entities.Teachers;
import com.example.demo.repositories.GradeRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.repositories.TeacherRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<Subjects> findStudentSubjectById(long id) {
		return subjectRepository.findByStudentId(id);
	}
	public Students findStudentById(long id) {
		return studentRepository.findById(id);
	}
	public Grades findStudentGradesById(long id) {
		return gradeRepository.findBySubjectId(id);
	}
	public Subjects findSubjectById(long id) {
		return subjectRepository.findById(id);
	}
	public Teachers findTeacherByStudentId(long id) {
		return teacherRepository.findById(id);
	}
	public void updateStudents(Students students) {
		
		Students findById = studentRepository.findById(students.getId());
		findById.setFirstname(students.getFirstname());
		findById.setMiddlename(students.getMiddlename());
		findById.setLastname(students.getLastname());
		findById.setUsername(students.getUsername());
		
		studentRepository.save(findById);
	}
	
	public void saveResetPassword(Students students) {
		
		students.setPassword(bcryptpassword(students.getPassword()));
		studentRepository.save(students);
		
	}
	
	private String bcryptpassword(String password) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder.encode(password);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
