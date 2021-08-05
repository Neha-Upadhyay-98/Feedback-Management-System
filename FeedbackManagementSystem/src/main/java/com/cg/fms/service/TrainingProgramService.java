package com.cg.fms.service;

/*****************************************************************
 *          @author          Pushpanjali Kumari
 *          Description      It is a service class for Program
 *                           @Service 
 *         Version             1.0
 *         Created On        28-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.exception.ProgramNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ICourseRepository;
import com.cg.fms.repository.ITrainingProgramRepository;

@Service
public class TrainingProgramService {

	@Autowired
	private ITrainingProgramRepository programrepository;
	
	@Autowired
	private ICourseRepository courserepository;
	
	public TrainingProgramService(ITrainingProgramRepository trainingprogramrepository) {
		super();
		this.programrepository = trainingprogramrepository;
	}
	
	public Program createProgram(Program pr) {
		Optional<Program> existingProgram = this.programrepository.findById(pr.getProgramId());
		if(existingProgram.isPresent()) {
			throw new RecordAlreadyExistsException("Program with id "+ pr.getProgramId()+" already exists");
		}
		return this.programrepository.save(pr);
	}

	public Program updateProgram(Program pr,String programId) {
		Program existingProgram = this.programrepository.getById(programId);
		existingProgram.setProgramId(pr.getProgramId());
		existingProgram.setProgramName(pr.getProgramName());
		existingProgram.setStartDate(pr.getStartDate());
		existingProgram.setEndDate(pr.getEndDate());
		existingProgram.settId(pr.gettId());
		return this.programrepository.save(existingProgram);
	}

	public ResponseEntity <Program> removeProgram(String programId) {
		Program existingProgram = this.programrepository.findById(programId)
				.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
		this.programrepository.delete(existingProgram);
		return ResponseEntity.ok().build();
	}

	public Program viewProgramById(String programId) {
		return this.programrepository.findById(programId)
				.orElseThrow(() -> new ProgramNotFoundException("Program not found with id " + programId));
	}
	
	public List<Program> viewAllPrograms() {
		return this.programrepository.findAll();
	}

	public Course addCourse(Course course) {
		Optional<Program> existingCourse = this.programrepository.findById(course.getCourseId());
		if(existingCourse.isPresent()) {
			throw new RecordAlreadyExistsException("Course with id "+ course.getCourseId()+" already exists");
		}
		return this.courserepository.save(course);
	}

	public ResponseEntity<Course> removeCourse(String courseid) {
		Course existingcourse = this.courserepository.findById(courseid)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseid));
		this.courserepository.delete(existingcourse);
		return ResponseEntity.ok().build();
	}

	public Course updateCourse(Course course, String courseId) {
		Course existingcourse = this.courserepository.getById(courseId);
		existingcourse.setCourseName(course.getCourseName());
		existingcourse.setCourseDescription(course.getCourseDescription());
		existingcourse.setNoOfDays(course.getNoOfDays());
		return this.courserepository.save(existingcourse);
	}

	public Course viewCourse(String courseId) {
		return this.courserepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
	}

	public List<Course> viewAllCourses() {
		return this.courserepository.findAll();
	}

	public List<Program> viewAllProgramsByDate(String startDate) {
		return this.programrepository.findAllProgramsByStartDate(startDate);
	}

	public List<Program> viewAllProgramsByTrainer(String trainerid) {
		return this.programrepository.findAllProgramsBytId(trainerid);
	}
}
