package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Teachers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "*Firstname is required")
	private String firstname;
	
	@NotBlank(message = "*Middlename is required")
	private String middlename;
	
	@NotBlank(message = "*Lastname is required")
	private String lastname;
	
	@NotBlank(message = "*Username is required")
	private String username;
	
	@Size(min = 7 , max = 100 , message = "Password must be 7 or more characters")
	private String password;
	private String role;
	
	@OneToMany(mappedBy = "teacher" ,orphanRemoval = true , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Students> students;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Students> getStudents() {
		return students;
	}

	public void setStudents(List<Students> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Teachers [id=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", username=" + username + ", password=" + password + ", role=" + role + ", students="
				+ students + "]";
	}
	
	
	
}
