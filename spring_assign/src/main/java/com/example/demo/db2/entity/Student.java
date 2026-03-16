package com.example.demo.db2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STU")
public class Student {

	
	
	private int rollid;
	@Id
@Column(name = "reg_no")
private int regNo;

public int getRegNo() {
    return regNo;
}

public void setRegNo(int regNo) {
    this.regNo = regNo;
}





	private String gender;
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	private int percentage;
	public int getPercentage() {
		return percentage;
	}


	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}


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
