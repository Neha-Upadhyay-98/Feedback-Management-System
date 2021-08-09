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


class ParticipantTest {
	static Participant pt;
	Participant pt1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pt= new Participant("12334456","Participant","Participant@1234","passworD@134");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		pt=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		pt1=new Participant();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		pt1=null;
	}
	
	@Test
	void testParticipant() {
		assertNotEquals(new Participant(),pt1);
	}

	

	/**
	 * Test method for {@link com.cg.fms.entity.Participant#getParticipantId()}.
	 */
	@Test
	void testGetParticipantId() {
		assertEquals("12334456",pt.getParticipantId());
		
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Participant#getParticipantName()}.
	 */
	@Test
	void testGetParticipantName() {
		assertEquals("Participant",pt.getParticipantName());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Participant#getParticipantPassword()}.
	 */
	@Test
	void testGetParticipantPassword() {
		assertEquals("passworD@134",pt.getParticipantPassword());
	}

}
