package com.cg.fms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.exception.ProgramNotFoundException;
import com.cg.fms.repository.ICourseRepository;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class TrainingProgramService implements ITrainingProgramService{

	@Autowired
	private ITrainingProgramRepository programrepository;
	
	@Autowired
	private ICourseRepository courserepository;

	@Override
	public Program createProgram(Program pr) {
		return this.programrepository.save(pr);
	}

	@Override
	public Program updateProgram(Program pr,long programId) {
		Program existingProgram=this.programrepository.getById(programId);
		existingProgram.setCourse(pr.getCourse());
		existingProgram.setStartDate(pr.getStartDate());
		existingProgram.setEndDate(pr.getEndDate());
		existingProgram.setFaculty(pr.getFaculty());
		return this.programrepository.save(existingProgram);
	}

	@Override
	public ResponseEntity <Program> removeProgram(long programId) {
		Program existingProgram = this.programrepository.findById(programId)
				.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
		this.programrepository.delete(existingProgram);
		return ResponseEntity.ok().build();
	}

	@Override
	public Program viewProgram(long programId) {
		return this.programrepository.findById(programId)
				.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
	}

	@Override
	public Course addCourse(Course course) {
		return this.courserepository.save(course);
	}

	@Override
	public ResponseEntity<Course> removeCourse(long courseid) {
		Course existingcourse = this.courserepository.findById(courseid)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseid));
		this.courserepository.delete(existingcourse);
		return ResponseEntity.ok().build();
	}

	@Override
	public Course updateCourse(Course course, long courseId) {
		Course existingcourse = this.courserepository.getById(courseId);
		existingcourse.setCourseName(course.getCourseName());
		existingcourse.setCourseDescription(course.getCourseDescription());
		existingcourse.setNoOfDays(course.getNoOfDays());
		return this.courserepository.save(existingcourse);
	}

	@Override
	public Course viewCourse(long courseId) {
		return this.courserepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
	}

	@Override
	public List<Course> viewAllCourses() {
		return this.courserepository.findAll();
	}

	@Override
	public List<Program> viewAllPrograms() {
		return this.programrepository.findAll();
	}

	@Override
	public List<Program> viewAllProgramsByDate(LocalDate date) {
		return this.programrepository.viewAllProgramsByDate(date);
	}

	@Override
	public List<Program> viewAllProgramsByFaculty(long facultyid) {
		return this.programrepository.viewAllProgramsByFaculty(facultyid);
	}
}
