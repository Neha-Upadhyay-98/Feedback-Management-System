package com.cg.fms.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;

public interface ITrainingProgramService {

	public Program createProgram(Program pr);
	public Program updateProgram(Program pr);
	public Program removeProgram(int programid);
	public Program viewProgram(int programid);
	public Course addCourse(Course course);
	public Course removeCourse(int courseid);
	public Course updateCourse(Course course);
	public Course viewCourse(int cid);
	public List<Course> viewAllCourses();
	public List<Program> viewAllPrograms();
	public List<Program> viewAllProgramsByDate(LocalDate date);
	public List<Program> viewAllProgramsByFaculty(int facultyid);
}
