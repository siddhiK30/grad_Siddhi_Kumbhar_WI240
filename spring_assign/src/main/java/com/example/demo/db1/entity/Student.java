package com.example.demo.db1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STU")
public class Student {

	
	@Id
	private int rollid;
	private String name;
	public int getRollid() {
		return rollid;
	}


	public void setRollid(int rollid) {
		this.rollid = rollid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStd() {
		return std;
	}


	public void setStd(int std) {
		this.std = std;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	private int std;
	

	private String school;
	

	
}
