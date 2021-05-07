package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Roles;
import com.example.demo.entities.Students;
import com.example.demo.entities.Teachers;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;

@Service
public class AdminService {


	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public void addTeacher(Teachers teachers) {	
		teachers.setRole(Roles.teacherRole);
		teacherRepository.save(teachers);
	}
	public List<Teachers> findAllTeachers() {
		return teacherRepository.findAll();
	}
	public List<Students> findAllStudents() {
		return studentRepository.findAll();
	}
	public void deleteTeacherById(long id) {
		teacherRepository.deleteById(id);
	}
	public Teachers findTeacherById(long id) {
		return teacherRepository.findById(id);
	}
	public void saveUpdateTeacher(Teachers teachers) {
		Teachers findById = teacherRepository.findById(teachers.getId());
		findById.setFirstname(teachers.getFirstname());
		findById.setMiddlename(teachers.getMiddlename());
		findById.setLastname(teachers.getLastname());
		findById.setUsername(teachers.getUsername());
		teacherRepository.save(findById);
	}
	public void saveResetPassword(Teachers teachers) {
		
		Teachers findById = teacherRepository.findById(teachers.getId());
		findById.setPassword(bcryptPassword(teachers.getPassword()));
		teacherRepository.save(findById);
	}
	
	
	
	private String bcryptPassword(String password) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt.encode(password);
	}
	
}
