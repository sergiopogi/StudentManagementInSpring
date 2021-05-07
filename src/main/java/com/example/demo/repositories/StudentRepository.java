package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

	public List<Students> findByTeacherid(long id);
	public Students findById(long id);
	public Students findByUsername(String username);
}
