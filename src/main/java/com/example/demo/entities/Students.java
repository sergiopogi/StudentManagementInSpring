package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Students {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Firstname is required")
	private String firstname;
	
	@NotBlank(message = "Middlename is required")
	private String middlename;
	
	@NotBlank(message = "Lastname is required")
	private String lastname;
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@Size(min = 7 , max = 100 , message = "Password must be 7 or more characters")
	private String password;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private Integer section;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "teacherid" ,insertable = false , updatable = false)
	private Teachers teacher;
	private long teacherid;
	
	@OneToMany(mappedBy = "student" ,targetEntity = Subjects.class ,orphanRemoval = true , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Subjects> subject;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Teachers getTeacher() {
		return teacher;
	}

	public void setTeacher(Teachers teacher) {
		this.teacher = teacher;
	}

	public long getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}

	public List<Subjects> getSubject() {
		return subject;
	}

	public void setSubject(List<Subjects> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", username=" + username + ", password=" + password + ", year=" + year + ", section="
				+ section + ", teacher=" + teacher + ", teacherid=" + teacherid + ", subject=" + subject + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	

}
