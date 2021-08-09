package com.cg.fms.entity;

/*****************************************************************
 *          @author          Sneha Chakraborty
 *          Description      It is an entity class for Trainer
 *                           @Entity 
 *         Version             1.0
 *         Created On        22-July-2021
 ******************************************************************/

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Trainer {

	@Id
	@Column(name="Trainer_Id", nullable = false)
	private String trainerId;
	
	@Column(name="Trainer_Name", nullable = false)
	private String trainerName;
	
	@Column(name="Trainer_Username", nullable = false)
	@NotEmpty(message = "Username is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#$]).{5,16}$", message = "Username is invalid")
	private String trainerUserName;
	
	@Column(name="Trainer_Password", nullable = false)
	@NotEmpty(message = "Password is required")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&]).{8,32}$", message= "Password is invalid")
	private String trainerPassword;
	
	@Column(name = "Role", nullable = false)
	private final String role = "Trainer";
	
	@Column(name = "Skill", nullable = false)
	private String skill;
	
	@Column(name="Feedback_List")
	@OneToMany(targetEntity = Feedback.class, mappedBy="trnrId", fetch=FetchType.LAZY)
	private List<Feedback> feedbackList;
	
	//Constructors
	public Trainer() {
		super();
	}

	public Trainer(String trainerId, String trainerName, String trainerUserName, String trainerPassword, String skill, 
			List<Feedback> feedbackList) {
		super();
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.trainerUserName = trainerUserName;
		this.trainerPassword = trainerPassword;
		this.skill = skill;
		this.feedbackList = feedbackList;
	}

	//Getters & Setters
	public String getTrainerId() {
		return trainerId;
	}
	
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	
	public String getTrainerUserName() {
		return trainerUserName;
	}

	public void setTrainerUserName(String trainerUserName) {
		this.trainerUserName = trainerUserName;
	}

	public String getTrainerPassword() {
		return trainerPassword;
	}

	public void setTrainerPassword(String trainerPassword) {
		this.trainerPassword = trainerPassword;
	}

	public String getRole() {
		return role;
	}
	
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	@Override
	public String toString() {
		return "Trainer [trainerId = " + trainerId + ", trainerName = " + trainerName + ", trainerUserName = " + trainerUserName + 
				", trainerPassword = " + trainerPassword + ", role = " + role + ", skill = " + skill + 
				", feedbacks = " + feedbackList + "]";
	}
}