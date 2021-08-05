package com.cg.fms.controller;

/*****************************************************************
 *          @author          Pushpanjali Kumari
 *          Description      It is an controller class for Program
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 ******************************************************************/

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Course;
import com.cg.fms.entity.Program;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.service.CourseService;
import com.cg.fms.service.TrainingProgramService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fms/program")
@Api(value = "Feedback Management System",description = "Operations pertaining to Program of Feedback Management System")
@Validated
public class TrainingProgramController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public TrainingProgramService programservice;
	
	@Autowired
	public CourseService courseservice;

	// Program CRUD
	@ApiOperation(value = "Add a Program")
	@PostMapping("/add")	
	public Program createProgram(@ApiParam(value = "program object stored in database table", required = true)@Valid @RequestBody Program pr) {
		logger.trace("createProgram method in TrainingProgram controller accessed");
		return this.programservice.createProgram(pr);
	}
	
	@ApiOperation(value = "Update a Program")
	@PutMapping("/update/{id}")
	public Program updateProgram(@ApiParam(value = "Update program object", required = true)@Valid @RequestBody Program pr,@ApiParam(value = "Program Id to update program object", required = true)  @PathVariable("id") String programId) {
		logger.trace("updateProgram method in TrainingProgram controller accessed");
		return this.programservice.updateProgram(pr, programId);
	}
	
	@ApiOperation(value = "Delete a Program")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity <Program> removeProgram(@ApiParam(value = "Program Id to delete program object from database table") @PathVariable("id") String programId){
		logger.trace("removeProgram method in TrainingProgram controller accessed");
		return this.programservice.removeProgram(programId);
	}
	
	@ApiOperation(value = "Get a program by Id")
	@GetMapping("/getbyid/{id}")
	public Program viewProgramById(@ApiParam(value = "Program id from which program object will be retrieved", required = true) @PathVariable("id") String programId) {
		logger.trace("viewProgram method in TrainingProgram controller accessed");
		return this.programservice.viewProgramById(programId);
	}
	
	@ApiOperation(value = "Get a list of program by Start Date")
	@GetMapping("/getbysdate/{sdate}")
	public List<Program> getProgramsByStartDate(@ApiParam(value = "Program start date from which program object will be retrieved", required = true)@PathVariable("sdate") String startDate) {
		logger.trace("getProgramsByStartDate method in TrainingProgram controller accessed");
		return this.programservice.viewAllProgramsByDate(startDate);
	}
	
	@ApiOperation(value = "Get a list of program by Trainer Id")
	@GetMapping("/getbytrainer/{id}")
	public List<Program> getProgramsByTrainer(@ApiParam(value = "Trainer id from which program object will be retrieved", required = true)@PathVariable("id") String trainerId) {
		logger.trace("getProgramsByTrainer method in TrainingProgram controller accessed");
		return this.programservice.viewAllProgramsByTrainer(trainerId);
	}
	
    @ApiOperation(value = "View a list of programs", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getall")
	public List<Program> viewAllPrograms(){
    	logger.trace("viewAllPrograms method in TrainingProgram controller accessed");
		return this.programservice.viewAllPrograms();
	}
	
    
    // Course CRUD
	@ApiOperation(value = "Add a Course")
	@PostMapping("/addcourse")
	public String addCourse(@ApiParam(value = "course object store in database table", required = true)@Valid @RequestBody Course course) {
		logger.trace("addCourse method in TrainingProgram controller accessed");
		return this.courseservice.addCourse(course.getCourseId(),course.getCourseName(),course.getCourseDescription(),course.getNoOfDays(),course.getPrgId());
	}
	
	@ApiOperation(value = "Update a Course")
	@PutMapping("/updatecourse/{id}")
	public Course updateCourse(@ApiParam(value = "Update course object", required = true)@Valid @RequestBody Course course,@ApiParam(value = "Course Id to update course object", required = true)  @PathVariable("id") String courseId) {
		logger.trace("updateCourse method in TrainingProgram controller accessed");
		return this.courseservice.updateCourse(course, courseId);
	}
	
	@ApiOperation(value = "Delete a Course")
	@DeleteMapping("/deletecourse/{id}")
	public String removeCourse(@ApiParam(value = "Course Id to delete course object from database table")@PathVariable(value = "id") String courseId) {
		logger.trace("deleteCourse method in TrainingProgram controller accessed");
		try {
			courseservice.removeCourse(courseId);
			return "200";
		}
		catch(CourseNotFoundException e) {
			logger.warn("Failed to remove course");
			return e.getMessage();
		}
	}
	
	@ApiOperation(value = "Get a course by Id")
	@GetMapping("/getcoursebyid/{id}")
	public Course viewCourseById(@ApiParam(value = "Course id from which course object will be retrieved", required = true) @PathVariable("id") @Positive String courseId) {
    	logger.trace("viewCourseById method in TrainingProgram controller accessed");
		return this.courseservice.viewCourseById(courseId);
	}
	
    @ApiOperation(value = "View a list of courses", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getallcourse")
	public List<Course> viewAllCourses() {
    	logger.trace("viewAllCourses method in TrainingProgram controller accessed");
		return this.courseservice.viewAllCourses();
	}
    
}