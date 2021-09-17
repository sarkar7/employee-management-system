package com.codeonist.ems.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String office;
	private String position;
	private Integer age;
	private LocalDate startDate;
	private Double salary;

	public Employee() {
	}

	public Employee(Long id, String name, String office, String position, Integer age, LocalDate startDate, Double salary) {
		this.id = id;
		this.name = name;
		this.office = office;
		this.position = position;
		this.age = age;
		this.startDate = startDate;
		this.salary = salary;
	}
	
	public Employee(String name, String office, String position, Integer age, LocalDate startDate, Double salary) {
		this.name = name;
		this.office = office;
		this.position = position;
		this.age = age;
		this.startDate = startDate;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
