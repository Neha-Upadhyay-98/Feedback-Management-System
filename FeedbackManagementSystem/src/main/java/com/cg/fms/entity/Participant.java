package com.cg.fms.entity;

/*****************************************************************
 *          @author          Neha Upadhyay
 *          Description      It is an entity class for Participant
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
@Table(name = "Participant")
public class Participant {
	
	@Id
	@Column(name="Participant_Id", nullable = false)
	private String participantId;
	
	@Column(name="Participant_Name", nullable = false)
	private String participantName;
	
	@Column(name="Participant_Username", nullable = false)
	@NotEmpty(message = "Username is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#$]).{5,16}$", message = "Username is invalid")
	private String participantUserName;
	
	@Column(name="Participant_Password", nullable = false)
	@NotEmpty(message = "Password is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&]).{8,32}$", message= "Password is invalid")
	private String participantPassword;
	
	@Column(name = "Role")
	private final String role = "Participant";
	
	//Constructors
	public Participant(String participantId, String participantName, String participantUserName, String participantPassword) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
		this.participantUserName = participantUserName;
		this.participantPassword = participantPassword;
	}
	
	public Participant() {
		super();
	}

	//Getter & Setters
	public String getRole() {
		return role;
	}
	public String getParticipantId() {
		return participantId;
	}
	
	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	
	public String getParticipantUserName() {
		return participantUserName;
	}

	public void setParticipantUserName(String participantUserName) {
		this.participantUserName = participantUserName;
	}

	public String getParticipantPassword() {
		return participantPassword;
	}

	public void setParticipantPassword(String participantPassword) {
		this.participantPassword = participantPassword;
	}
	
	@Override
	public String toString() {
		return "Participant [participantId = " + participantId + ", participantName = " + participantName + 
				", participantUserName = " + participantUserName + ", participantPassword = " + participantPassword + 
				", role = " + role + "]";
	}

}