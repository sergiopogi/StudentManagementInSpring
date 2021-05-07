package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Teachers;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Long> {

	public Teachers findByUsername(String username);
	public Teachers findById(long id);
}
