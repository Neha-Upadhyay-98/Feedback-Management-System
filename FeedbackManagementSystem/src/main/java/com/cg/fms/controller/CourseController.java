package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.entity.Course;
import com.cg.fms.service.ICourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	public ICourseService courseservice;
	
	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		return this.courseservice.addCourse(course);
	}
	
	@PutMapping("/updatecourse/{id}")
	public Course updateCourse(@RequestBody Course course, @PathVariable("id") long courseId) {
		return this.courseservice.updateCourse(course, courseId);
	}
	
	@DeleteMapping("/deletecourse/{id}")
	public ResponseEntity <Course> removeCourse(@PathVariable("id") long courseId) {
		return this.courseservice.removeCourse(courseId);
	}
	
	@GetMapping("/getcourse")
	public List<Course> viewAllCourses() {
		return this.courseservice.viewAllCourses();
	}
	
	@GetMapping("/getcoursebyid/{id}")
	public Course viewCourse(@PathVariable("id") long courseId) {
		return this.courseservice.viewCourse(courseId);
	}
	
}
