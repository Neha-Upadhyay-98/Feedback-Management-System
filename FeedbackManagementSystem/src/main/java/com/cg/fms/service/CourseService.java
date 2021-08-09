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
import com.cg.fms.exception.UserNotLoggedInException;
import com.cg.fms.repository.ICourseRepository;

@Service
public class CourseService {

	@Autowired
	private ICourseRepository courserepository;
	
	@Autowired
	public AdminService adminservice;
	
	public CourseService(ICourseRepository courseRepository) {
		this.courserepository = courseRepository;
	}

	public String addCourse(String courseId, String courseName, String courseDescription, String noOfDays, String prgId, boolean test) throws RecordAlreadyExistsException {
		if(test || this.adminservice.flag==1) {
			Course course = new Course(courseId,courseName,courseDescription, noOfDays, prgId);
			if(courserepository.existsById(courseId)) {
				throw new RecordAlreadyExistsException ("Course with id " + course.getCourseId() + " & name" + course.getCourseName() + " already exists!");
			}
			else {
				this.courserepository.save(course);
				return "Course added";
			}
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
		
	}

	public Course updateCourse(Course course, String courseId) {
		if(this.adminservice.flag==1) {
			Course existingcourse = this.courserepository.getById(courseId);
			existingcourse.setCourseName(course.getCourseName());
			existingcourse.setCourseDescription(course.getCourseDescription());
			existingcourse.setNoOfDays(course.getNoOfDays());
			return this.courserepository.save(existingcourse);
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
		
	}
	
	public void removeCourse(String courseId) throws CourseNotFoundException {
		if(this.adminservice.flag==1) {
			courserepository.findById(courseId)
			.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
		courserepository.deleteById(courseId);
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}

	public Course viewCourseById(String courseId) {
		if(this.adminservice.flag==1) {
			return this.courserepository.findById(courseId).get();
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}
	
	public List<Course> viewAllCourses() {
		if(this.adminservice.flag==1) {
			return this.courserepository.findAll();
		}
		else {
			throw new UserNotLoggedInException("Admin not logged in");
		}
	}
	
}