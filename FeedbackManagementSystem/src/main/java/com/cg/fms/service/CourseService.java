package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.repository.ICourseRepository;

@Service
public class CourseService implements ICourseService{

	@Autowired
	private ICourseRepository courserepository;

	@Override
	public Course addCourse(Course course) {
		return this.courserepository.save(course);
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
	public ResponseEntity<Course> removeCourse(long courseId) {
		Course existingcourse = this.courserepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
		this.courserepository.delete(existingcourse);
		return ResponseEntity.ok().build();
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
	
}
