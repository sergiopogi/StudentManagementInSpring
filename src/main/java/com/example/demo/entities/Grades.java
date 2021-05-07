package com.example.demo.entities;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Grades {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Integer firstgrading;
	private Integer secondgrading;
	private Integer thirdgrading;
	private Integer fourthgrading;
	
	@OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "subjectid" ,insertable = false , updatable = false)
	private Subjects subject;
	private long subjectid;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getFirstgrading() {
		return firstgrading;
	}

	public void setFirstgrading(Integer firstgrading) {
		this.firstgrading = firstgrading;
	}

	public Integer getSecondgrading() {
		return secondgrading;
	}

	public void setSecondgrading(Integer secondgrading) {
		this.secondgrading = secondgrading;
	}

	public Integer getThirdgrading() {
		return thirdgrading;
	}

	public void setThirdgrading(Integer thirdgrading) {
		this.thirdgrading = thirdgrading;
	}

	public Integer getFourthgrading() {
		return fourthgrading;
	}

	public void setFourthgrading(Integer fourthgrading) {
		this.fourthgrading = fourthgrading;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	
	

	
	
}
