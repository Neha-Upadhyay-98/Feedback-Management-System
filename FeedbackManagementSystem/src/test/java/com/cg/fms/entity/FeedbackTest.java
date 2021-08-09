/**
 * 
 */
package com.cg.fms.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FeedbackTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Feedback fd;
	Feedback fd1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fd=new Feedback(null, null, 0, 0, 0, 0, 0, null, null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	fd=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		fd1=new Feedback();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		fd1=null;
	}
	@Test
	void testFeedback() {
		assertNotEquals(new Feedback(),fd1);
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Feedback#getFeedbackId()}.
	 */
	@Test
	void testGetFeedbackId() {
		assertEquals(0,fd.getFeedbackId());
	}

	
}
