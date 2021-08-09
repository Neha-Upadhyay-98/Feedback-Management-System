package com.cg.fms.entity;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is an entity class for Login
 *                           @Entity 
 *         Version             1.0
 *         Created On        22-July-2021
 ******************************************************************/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Login {
	@Id
	@Column(name="userId", nullable=false)
	private String userid;
	
	@Column(name="Username", nullable = false)
	@NotEmpty(message = "Username is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#$]).{5,16}$", message = "Username is invalid")
	private String userName;
	
	@Column(name="Password", nullable = false)
	@NotEmpty(message = "Password is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&]).{8,32}$", message = "Password is invalid")
	private String password;
	
	@Column(name="Status", nullable = false)
	private boolean status = false;
	
	//Constructors
	public Login() {
		super();
	}
	public Login(String userid, String userName, String password) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
	}
		
	//Getters & Setters
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Login [userid = " + userid + ", userName = " + userName + ", password = " + password + ", status = " + status + "]";
	}
	
}
