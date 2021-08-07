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

class ProgramTest {
	static Program pro;
	Program pro1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pro= new Program("Pr101", "SpringBoot", null, null, null, null, null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		pro=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		pro1=new Program();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		pro1=null;
	}
	
	@Test
	void testProgram() {
		assertNotEquals(new Program(),pro1);
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Program#getProgramId()}.
	 */
	@Test
	void testGetProgramId() {
		assertEquals("Pr101",pro.getProgramId());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Program#getProgramName()}.
	 */
	@Test
	void testGetProgramName() {
		assertEquals("SpringBoot",pro.getProgramName());
	}

}
