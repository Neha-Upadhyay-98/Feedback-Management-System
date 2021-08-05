/**
 * 
 */
package com.cg.fms.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainerTest {
	static Trainer tr;
	Trainer tr1;
	private static List<Feedback> feedbacklist= new ArrayList<Feedback>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Feedback feedback=new Feedback("P001","TR001",1,2,3,4,5,null,null);
    	feedbacklist.add(feedback);
		tr=new Trainer("TR001", "Swapna","Trainer@123","Swapna@123","FullStack", feedbacklist);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		tr=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		tr1=new Trainer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		tr1=null;
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Trainer#getTrainerId()}.
	 */
	@Test
	void testGetTrainerId() {
		assertEquals("TR001",tr.getTrainerId());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Trainer#getTrainerName()}.
	 */
	@Test
	void testGetTrainerName() {
		assertEquals("Swapna",tr.getTrainerName());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Trainer#getTrainerPassword()}.
	 */
	@Test
	void testGetTrainerPassword() {
		assertEquals("Swapna@123",tr.getTrainerPassword());
	}

	
}
