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


class AdminTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Admin adm;
	Admin adm1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	adm=new Admin("00111","Anjali","Anjali@1234","Anjali@123");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	adm=null; 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		adm1= new Admin("00111","Anjali","Anjali@1234","Anjali@123");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	adm1=null;
	}
	
	@Test
	void testAdmin() {
		assertNotEquals(new Admin("00111","Anjali","Anjali@1234","Anjali@123"),adm1);
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Admin#getAdminId()}.
	 */
	@Test
	void testGetAdminId() {
		assertEquals("00111",adm.getAdminId());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Admin#getAdminName()}.
	 */
	@Test
	void testGetAdminName() {
		assertEquals("Anjali",adm.getAdminName());
	}

	/**
	 * Test method for {@link com.cg.fms.entity.Admin#getAdminPassword()}.
	 */
	@Test
	void testGetAdminPassword() {
		assertEquals("Anjali@123",adm.getAdminPassword());
	}

}
