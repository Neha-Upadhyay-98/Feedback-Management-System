package com.cg.fms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "course_Sequence")
    @SequenceGenerator(name = "course_Sequence", sequenceName = "COURSE_SEQ")
	@Column(name = "courseid")
	private long courseId;
	
	@Column(name = "coursename")
	private String courseName;
	
	@Column(name = "coursedescription")
	private String courseDescription;
	
	@Column(name = "courseduration")
	private int noOfDays;
	
	//Constructors
	public Course(long courseId, String courseName, String courseDescription, int noOfDays) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.noOfDays = noOfDays;
	}
	public Course() {
		super();
	}
	
	//Getters & Setters
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
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
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	
}
