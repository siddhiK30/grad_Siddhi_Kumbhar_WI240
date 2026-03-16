package com.example.demo.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "STU")
public class Student {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private int rollid;

    private String name;

    private String school;

    private int std;

    private String gender;

    private int percentage;
 @Column(name = "reg_no")
    private int regNo;

    public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public Student() {}

    public int getRollid() { return rollid; }
    public void setRollid(int rollid) { this.rollid = rollid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }

    public int getStd() { return std; }
    public void setStd(int std) { this.std = std; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getPercentage() { return percentage; }
    public void setPercentage(int percentage) { this.percentage = percentage; }

   
}