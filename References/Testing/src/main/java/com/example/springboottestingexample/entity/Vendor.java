package com.example.springboottestingexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendors")
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Email should not be empty")
	@Column(nullable = false, unique = true, length = 100)
	private String venEmail;

	@Column(nullable = false, length = 100)
	private String venPassword;

	@Column(nullable = false, length = 100)
	private String venName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVenEmail() {
		return venEmail;
	}

	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}

	public String getVenPassword() {
		return venPassword;
	}

	public void setVenPassword(String venPassword) {
		this.venPassword = venPassword;
	}

	public String getVenName() {
		return venName;
	}

	public void setVenName(String venName) {
		this.venName = venName;
	}
	
	
	
}
