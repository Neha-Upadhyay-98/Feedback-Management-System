package com.cg.fms.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "program")
public class Program {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "program_Sequence")
    @SequenceGenerator(name = "program_Sequence", sequenceName = "PROGRAM_SEQ")
	private long trainingId;
	
	@Column(name = "startdate")
	private LocalDate startDate;
	
	@Column(name = "enddate")
	private LocalDate endDate;
	
	@Column(name = "course")
	private Course course;
	
	@Column(name = "faculty")
	private Employee faculty;

	public Program() {
		super();
	}

	public Program(LocalDate startDate, LocalDate endDate, Course course, Employee faculty) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = course;
		this.faculty = faculty;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Employee getFaculty() {
		return faculty;
	}

	public void setFaculty(Employee faculty) {
		this.faculty = faculty;
	}
	
	
}
