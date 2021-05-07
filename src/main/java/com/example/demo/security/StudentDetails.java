package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Students;

public class StudentDetails implements UserDetails {
	
	private Students students;
	
	

	public StudentDetails(Students students) {
	
		this.students = students;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthority = new ArrayList<>();
		grantedAuthority.add(new SimpleGrantedAuthority(students.getRole()));
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return students.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return students.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public long getId() {
		return students.getId();
	}
	
	public long getTeacherId() {
		return students.getTeacherid();
	}

}
