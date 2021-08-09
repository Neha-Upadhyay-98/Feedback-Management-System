package com.cg.fms.entity;

/*****************************************************************
 *          @author          Sneha Chakraborty
 *          Description      It is an entity class for Program
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

@Entity
public class Program {

	@Id
	@Column(name="Program_Id",unique=true, nullable = false)
	private String programId;
	
	@Column(name = "Program_Name", nullable=false)
	private String programName ;

	@Column(name = "Start_Date", nullable=false)
	private String startDate;

	@Column(name = "End_Date", nullable=false)
	private String endDate;

	/*
	 * relation between training program and trainer
	 * a program can have many trainers as well as a trainer can be assigned to different programs
	 */
	@Column(name = "TId", nullable = false)
	private String tId;
	
	@Column(name="Course_List")
	@OneToMany(targetEntity = Course.class, mappedBy="prgId", fetch=FetchType.LAZY)
	private List<Course> courseList;
	
	@Column(name="Feedback_List")
	@OneToMany(targetEntity = Feedback.class, mappedBy="prgId", fetch=FetchType.LAZY)
	private List<Feedback> feedbackList;
	
	/*
	 * Default Constructor
	 */
	public Program() {
		super();
	}

	/*
	 * Parameterized Constructor
	 */
	public Program(String programId, String programName, String startDate, String endDate, String tId,
			List<Course> courseList, List<Feedback> feedbackList) {
		super();
		this.programId = programId;
		this.programName = programName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tId = tId;
		this.courseList = courseList;
		this.feedbackList = feedbackList;
	}
	
	/*
	 * Getters and Setters
	 */
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	@Override
	public String toString() {
		return "Program [programId = " + programId + ", programName = " + programName + ", startdate = " + startDate + 
				", enddate = " + endDate + ", tid = " + tId + ", courseList = " + courseList + ", feedbackList = " + feedbackList + "]";
	}
	
}