package com.skilldistillery.todoapp.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todoapp.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	Set<Todo> findByUser_Username(String username);
	Todo findByIdAndUserUsername(int id, String username);
}
