package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMP")
public class Employee {
	
	@Id
	private int eid;
	private String name;
	private int salary;
	private int age;
	@Column(name = "ROLE")
	private String deisgnation;
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDeisgnation() {
		return deisgnation;
	}
	public void setDeisgnation(String deisgnation) {
		this.deisgnation = deisgnation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
	    return "Employee [eid=" + eid +
	           ", name=" + name +
	           ", salary=" + salary +
	           ", designation=" + deisgnation + "]";
	}

	
}
