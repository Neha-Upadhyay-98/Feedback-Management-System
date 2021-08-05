package com.cg.fms.service;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is a service class for Course
 *                           @Service 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.exception.RecordAlreadyExistsException;
import com.cg.fms.repository.ICourseRepository;

@Service
public class CourseService {

	@Autowired
	private ICourseRepository courserepository;
	
	public CourseService(ICourseRepository courseRepository) {
		this.courserepository = courseRepository;
	}

	public String addCourse(String courseId, String courseName, String courseDescription, String noOfDays, String prgId) throws RecordAlreadyExistsException {
		Course course = new Course(courseId,courseName,courseDescription, noOfDays, prgId);
		if(courserepository.existsById(courseId)) {
			throw new RecordAlreadyExistsException ("Course with id " + course.getCourseId() + " & name" + course.getCourseName() + " already exists!");
		}
		else {
			this.courserepository.save(course);
			return "Course added";
		}
	}

	public Course updateCourse(Course course, String courseId) {
		Course existingcourse = this.courserepository.getById(courseId);
		existingcourse.setCourseName(course.getCourseName());
		existingcourse.setCourseDescription(course.getCourseDescription());
		existingcourse.setNoOfDays(course.getNoOfDays());
		return this.courserepository.save(existingcourse);
	}
	
	public void removeCourse(String courseId) throws CourseNotFoundException {
		courserepository.findById(courseId)
			.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
		courserepository.deleteById(courseId);

	}

	public Course viewCourseById(String courseId) {
		return this.courserepository.findById(courseId).get();
	}
	
	public List<Course> viewAllCourses() {
		return this.courserepository.findAll();
	}
	
}