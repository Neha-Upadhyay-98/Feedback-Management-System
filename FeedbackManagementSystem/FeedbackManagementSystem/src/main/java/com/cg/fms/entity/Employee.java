package com.cg.fms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "employee_Sequence")
    @SequenceGenerator(name = "employee_Sequence", sequenceName = "EMPLOYEE_SEQ")
	@Column(name = "employeeid")
	private int employeeId;
	
	@Column(name = "empname")
	private String empName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private Role role;

	//constructors
	public Employee() {
		super();
	}

	public Employee(String empName, String password, Role role) {
		super();
		
		this.empName = empName;
		this.password = password;
		this.role = role;
	}
	//Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

			
}
