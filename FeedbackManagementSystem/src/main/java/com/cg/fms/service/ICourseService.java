package com.cg.fms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;

@Service
public interface ICourseService {
	
	Course addCourse(Course course);
	Course updateCourse(Course course, long courseId);
	ResponseEntity <Course> removeCourse(long courseId);
	Course viewCourse(long courseId);
	List<Course> viewAllCourses();

}
