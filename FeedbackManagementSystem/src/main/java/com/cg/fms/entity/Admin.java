package com.cg.fms.entity;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is an entity class for Admin
 *                           @Entity 
 *         Version             1.0
 *         Created On        22-July-2021
 ******************************************************************/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Admin")
public class Admin {
	
	@Id
	@Column(name="Admin_Id", nullable = false)
	private String adminId;
	
	@Column(name="Admin_Name", nullable = false)
	private String adminName;
	
	@Column(name="Admin_Username", nullable = false)
	@NotEmpty(message = "Username is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#$]).{5,16}$", message = "Username is invalid")
	private String adminUserName;
	
	@Column(name="Admin_Password", nullable = false)
	@NotEmpty(message = "Password is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&]).{8,32}$", message= "Password is invalid")
	private String adminPassword;
	
	@Column(name = "Role")
	private final String role = "Admin";

	//Constructors
	public Admin(String adminId, String adminName,String adminUserName, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	public Admin() {
		super();
	}

	//Getters & Setters
	public String getAdminId() {
		return adminId;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId = " + adminId + ", adminName = " + adminName + ", adminUserName=" + adminUserName +
				", adminPassword = " + adminPassword + ", role = " + role + "]";
	}

}