package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Roles;
import com.example.demo.entities.Students;
import com.example.demo.entities.Teachers;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public Teachers findTeacherById(long id) {
		  return  teacherRepository.findById(id);
	}
	public void updateTeacher(Teachers teacher , long teacherid) {
		
		Teachers findById = teacherRepository.findById(teacherid);
		findById.setFirstname(teacher.getFirstname());
		findById.setLastname(teacher.getLastname());
		findById.setMiddlename(teacher.getMiddlename());
		findById.setUsername(teacher.getUsername());
		teacherRepository.save(findById);
	}
	
	public void teacherPasswordReset(Teachers teachers , long teacherid) {
		Teachers findById = teacherRepository.findById(teacherid);
		findById.setPassword(passwordEncoder(teachers.getPassword()));
		teacherRepository.save(findById);
	}
	
	
	
	public void saveResetPasswordStudent(Students student) {
		student.setPassword(passwordEncoder(student.getPassword()));
		studentRepository.save(student);
	}
	
	public void addStudent(Students student , long teacherId) {
		student.setRole(Roles.studentRole);
		student.setTeacherid(teacherId);
		student.setPassword(passwordEncoder(student.getPassword()));
		
	    studentRepository.save(student );
	}
	public List<Students> findStudentsByTeacherId(long id) {
		return studentRepository.findByTeacherid(id);
	}
	public void deleteStudentById(long id) {
		 studentRepository.deleteById(id);
	}
	public Students findStudent(long id) {
		return studentRepository.findById(id);
	}
	public Students updateStudent(Students student) {
		
		Students findById = studentRepository.findById(student.getId());
		findById.setFirstname(student.getFirstname());
		findById.setMiddlename(student.getMiddlename());
		findById.setLastname(student.getLastname());
		findById.setYear(student.getYear());
		findById.setSection(student.getSection());
		 studentRepository.save(findById);
		 return findById;
	}

	private String passwordEncoder(String password)  {
		
		BCryptPasswordEncoder bcryptpassword = new BCryptPasswordEncoder();
		String encryptedPassword = bcryptpassword.encode(password);
		
		return encryptedPassword;
		
	}
}
