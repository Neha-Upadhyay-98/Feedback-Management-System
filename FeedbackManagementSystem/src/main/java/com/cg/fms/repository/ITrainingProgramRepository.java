package com.cg.fms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;

public interface ITrainingProgramRepository extends JpaRepository<Program, Long>{

	public Program createProgram(Program pr);
	public Program updateProgram(Program pr, long programId);
	public ResponseEntity <Program> removeProgram(long programid);
	public Program viewProgram(long programid);
	public Course addCourse(Course course);
	public ResponseEntity <Course> removeCourse(long courseid);
	public Course updateCourse(Course course, long courseId);
	public Course viewCourse(long courseid);
	public List<Course> viewAllCourses();
	public List<Program> viewAllPrograms();
	public List<Program> viewAllProgramsByDate(LocalDate date);
	public List<Program> viewAllProgramsByFaculty(long facultyid);
}
