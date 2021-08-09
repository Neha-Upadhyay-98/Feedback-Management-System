package com.cg.fms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.fms.entity.Course;
import com.cg.fms.exception.CourseNotFoundException;
import com.cg.fms.repository.ICourseRepository;

class CourseServiceTest {
	
	private CourseService courseService;
	private ICourseRepository courseRepository;
	
	@BeforeEach
	void setUp() {
		courseRepository = mock(ICourseRepository.class);
		courseService = new CourseService(courseRepository);
	}

	@Test
	void shouldSaveCourseSuccessfully() throws CourseNotFoundException {
		Course course = new Course("CS001","Java","J2EEFSD","28","PR001");
		given(courseRepository.findBycourseId(course.getCourseId())).willReturn(Optional.empty());
		given(courseRepository.save(course)).willAnswer(invocation -> invocation.getArgument(0));
		
		String savedCourse = courseService.addCourse(course.getCourseId(),course.getCourseName(),course.getCourseDescription(),course.getNoOfDays(),course.getPrgId(), true);
		assertThat(savedCourse).isNotNull();
		verify(courseRepository).save(any(Course.class));
	}

}
