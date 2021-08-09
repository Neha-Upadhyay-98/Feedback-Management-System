package com.cg.fms.repository;

/*****************************************************************
 *          @author          Joysurjya Bera
 *          Description      It is a repository interface for Course
 *                           @Repository 
 *         Version             1.0
 *         Created On        27-July-2021
 ******************************************************************/

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, String>{
	
	Course findByCourseId(String courseId);
	Optional <Course> findBycourseId(String courseId);
	
}