package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long>{

	public Course addCourse(Course course);
	public Course updateCourse(Course course, long courseId);
	public ResponseEntity <Course> removeCourse(long courseId);
	public Course viewCourse(long courseId);
	public List<Course> viewAllCourses();
	
}
