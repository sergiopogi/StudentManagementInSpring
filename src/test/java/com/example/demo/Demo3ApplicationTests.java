package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Teachers;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.service.TeacherService;

@SpringBootTest
class Demo3ApplicationTests {
	
	@Autowired
	private TeacherService teacherRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	void subjectList() {
		Teachers findById = teacherRepo.findTeacherById(2);
		System.out.println("TEACHER :"+findById.getFirstname());
		assertThat(findById).isNotNull();
	}
}
