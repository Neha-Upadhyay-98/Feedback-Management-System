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
import com.cg.fms.entity.Program;
import com.cg.fms.service.ICourseService;
import com.cg.fms.service.ITrainingProgramService;

@RestController
@RequestMapping("/api/program")
public class TrainingProgramController {
	
	@Autowired
	public ITrainingProgramService programservice;
	
	@Autowired
	public ICourseService courseservice;

	@PostMapping("/addprogram")
	public Program createProgram(@RequestBody Program pr) {
		return this.programservice.createProgram(pr);
	}
	
	@PutMapping("/updateprogram/{id}")
	public Program updateProgram(@RequestBody Program pr,@PathVariable("id") long programId) {
		return this.programservice.updateProgram(pr, programId);
	}
	
	@DeleteMapping("/deleteprogram/{id}")
	public ResponseEntity <Program> removeProgram(@PathVariable("id") long programId){
		return this.programservice.removeProgram(programId);
	}
	
	@GetMapping("/getprogrambyid/{id}")
	public Program viewProgram(@PathVariable("id") long programId) {
		return this.programservice.viewProgram(programId);
	}
	
	@GetMapping("/getallprograms")
	public List<Program> viewAllPrograms(){
		return this.programservice.viewAllPrograms();
	}
	
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
