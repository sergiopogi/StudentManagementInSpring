package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Subjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Subjectname is required")
	private String subjectname;
	@NotBlank(message = "Teachername is required")
	private String teachername;
	
	
	@ManyToOne
	@JoinColumn(name="studentid" ,referencedColumnName = "id",insertable = false ,updatable = false)
	private Students student;
	
	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "subject")
	private Grades grades;
	
	private long studentid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Grades getGrades() {
		return grades;
	}

	public void setGrades(Grades grades) {
		this.grades = grades;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	
	
	

}
