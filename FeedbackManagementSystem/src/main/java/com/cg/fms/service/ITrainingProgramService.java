package com.cg.fms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;

public interface ITrainingProgramService {

	public Program createProgram(Program pr);
	public Program updateProgram(Program pr, long programId);
	public ResponseEntity <Program> removeProgram(long programId);
	public Program viewProgram(long programId);
	public Course addCourse(Course course);
	public ResponseEntity <Course> removeCourse(long courseid);
	public Course updateCourse(Course course, long courseId);
	public Course viewCourse(long courseId);
	public List<Course> viewAllCourses();
	public List<Program> viewAllPrograms();
	public List<Program> viewAllProgramsByDate(LocalDate date);
	public List<Program> viewAllProgramsByFaculty(long facultyid);
	
}
