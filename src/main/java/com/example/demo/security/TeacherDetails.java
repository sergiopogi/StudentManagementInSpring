package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Teachers;

public class TeacherDetails implements UserDetails {
	
	
	private Teachers teachers;
	
	

	public TeacherDetails(Teachers teachers) {
		
		this.teachers = teachers;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthority = new ArrayList<>();
		grantedAuthority.add(new SimpleGrantedAuthority(teachers.getRole()));
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return teachers.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return teachers.getUsername();
	}
	
	public long getId() {
		return teachers.getId();
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

}
