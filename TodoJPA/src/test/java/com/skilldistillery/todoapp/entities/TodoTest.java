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

class TodoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;
	
	
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
	    todo = em.find(Todo.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	}
	
	@Test
	void test_todo_basic_mappings() {
		assertNotNull(todo);
		assertEquals("Go round Mum's", todo.getTask());
		assertEquals(null, todo.getDescription());
		assertTrue(todo.isCompleted());
		assertEquals(null, todo.getDueDate());
		assertEquals(null, todo.getCompleteDate());
		assertEquals(2024, todo.getCreatedAt().getYear());
		assertEquals(2024, todo.getUpdatedAt().getYear());
		
		
	}
	
	@Test
	void test_Todo_has_User_mapping() {
		assertNotNull(todo.getUser());
		assertEquals("shaun", todo.getUser().getUsername());
	}


}
