package com.cg.fms.controller;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is an controller class for Course
 *                           @RestController 
 *         Version             1.0
 *         Created On        29-July-2021
 ******************************************************************/

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fms/course")
@Api(value = "Feedback Management System", description = "Operations pertaining to Course of Feedback Management System")
@Validated
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public CourseService courseService;
	
	@ApiOperation(value = "Add an Course")
	@PostMapping(value = "/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String addCourse(@ApiParam(value = "Course info to add", required = true)@Valid @RequestBody Map<String, String> crs) {
    	logger.trace("signup method in login controller accessed");
    	try {
			return courseService.addCourse(crs.get("courseId"), crs.get("courseName"), crs.get("courseDescription"), crs.get("noOfDays"), crs.get("prgId"), false);
		}
		catch(RecordAlreadyExistsException e) {
			logger.warn("Course already exists");
			return e.getMessage();
		}
	}
	
	@ApiOperation(value = "Update a Course")
	@PutMapping("/update/{id}")
	public Course updateCourse(@ApiParam(value = "Update course object", required = true)@Valid @RequestBody Course course,@ApiParam(value = "Course Id to update course object", required = true)  @PathVariable("id") String courseId) {
		logger.trace("updateCourse method in course controller accessed");
		return this.courseService.updateCourse(course, courseId);
	}
	
	@ApiOperation(value = "Delete a Course")
	@DeleteMapping("/delete/{id}")
	public String removeCourse(@ApiParam(value = "Course Id to delete course object from database table")@PathVariable(value = "id") String courseId) {
		logger.trace("deleteCourse method in course controller accessed");
		try {
			courseService.removeCourse(courseId);
			return "200";
		}
		catch(CourseNotFoundException e) {
			logger.warn("Failed to remove course");
			return e.getMessage();
		}
	}
	
	@ApiOperation(value = "Get a course by Id")
	@GetMapping("/getbyid/{id}")
	public Course viewCourseById(@ApiParam(value = "Course id from which course object will be retrieved", required = true) @PathVariable("id") String courseId) {
    	logger.trace("viewCourseById method in course controller accessed");
		return this.courseService.viewCourseById(courseId);
	}
	
    @ApiOperation(value = "View a list of courses", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getall")
	public List<Course> viewAllCourses() {
    	logger.trace("viewAllCourses method in course controller accessed");
		return this.courseService.viewAllCourses();
	}
	
}