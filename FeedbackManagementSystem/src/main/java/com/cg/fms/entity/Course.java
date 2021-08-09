package com.cg.fms.entity;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is an entity class for Course
 *                           @Entity 
 *         Version             1.0
 *         Created On        22-July-2021
 ******************************************************************/

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
	
	@Id
	private String courseId;
	private String courseName;
	private String courseDescription;
	private String noOfDays;
	private String prgId;

	//Constructors
	public Course() {
		super();
	}

	public Course(String courseId, String courseName, String courseDescription, String noOfDays, String prgId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.noOfDays = noOfDays;
		this.prgId = prgId;
	}

	//Getters & Setters
	public String getCourseId() {
		return courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public String getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getPrgId() {
		return prgId;
	}
	public void setPrgId(String prgId) {
		this.prgId = prgId;
	}

	@Override
	public String toString() {
		return "Course [courseId = " + courseId + ", courseName = " + courseName + ", courseDescription = " + courseDescription +
				", noOfDays = " + noOfDays + ", prgId = " + prgId + "]";
	}
}