package com.skilldistillery.todoapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    emf = Persistence.createEntityManagerFactory("TodoJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	    emf.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	    em = emf.createEntityManager();
	    user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	}
	
	@Test
	void test_user_basic_mappings() {
		
		assertEquals("shaun@winchester.co.uk",user.getEmail());
		assertEquals("shaun",user.getUsername());
	}

	
	@Test
	void test_user_has_todos() {
		assertNotNull(user.getTodos());
		assertTrue(user.getTodos().size() > 0 );
	}
	
 
}
