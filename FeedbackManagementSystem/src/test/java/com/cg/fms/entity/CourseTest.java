/**
 * 
 */
package com.cg.fms.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Course cour;
	Course cour1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	cour=new Course("CS001","JAVA", "core JAVA", "60","PR001");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		cour=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cour1=new Course();
	}

	@Test
	public void testCourse() {
		assertNotEquals(new Course(null, null, null, null, null),cour1);
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Course#getCourseId()}.
	 */
	@Test
	void testGetCourseId() {
		assertNotEquals("C101",cour.getCourseId());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Course#getCourseName()}.
	 */
	@Test
	void testGetCourseName() {
		assertEquals("JAVA",cour.getCourseName());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Course#getCourseDescription()}.
	 */
	@Test
	void testGetCourseDescription() {
		assertEquals("core JAVA",cour.getCourseDescription());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Course#getNoOfDays()}.
	 */
	@Test
	void testGetNoOfDays() {
		assertEquals("60", cour.getNoOfDays());
	}

}
