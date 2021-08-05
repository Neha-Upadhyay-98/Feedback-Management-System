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

class LoginTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Login lg;
	Login lg1;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	lg=new Login("U101", "pushpanjali", "pushpanjali@123"); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	
	lg=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	
	lg1=new Login();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	
	lg1=null;
	}

	@Test
	void testLogin() {
		assertNotEquals(new Login(),lg1);
	}
	
	/**
	 * Test method for {@link com.cg.fms.entity.Login#getUserid()}.
	 */
	@Test
	void testGetUserid() {
		assertEquals("U101",lg.getUserid());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Login#getUserName()}.
	 */
	@Test
	void testGetUserName() {
		assertEquals("pushpanjali",lg.getUserName());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Login#getPassword()}.
	 */
	@Test
	void testGetPassword() {
		assertEquals("pushpanjali@123",lg.getPassword());
	}

}
