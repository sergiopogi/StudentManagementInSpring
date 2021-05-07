package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Students;
import com.example.demo.repositories.StudentRepository;

@Service("studentDetailsService")
public class StudentDetailsService implements UserDetailsService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Students students = studentRepository.findByUsername(username);
		
		if(students == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return new StudentDetails(students);
	}

}
