package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Teachers;
import com.example.demo.repositories.TeacherRepository;

@Service("teacherDetailsService")
public class TeacherDetailsService implements  UserDetailsService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Teachers findByUsername = teacherRepository.findByUsername(username);
		
		if(findByUsername == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		
		return new TeacherDetails(findByUsername);
	}

}
