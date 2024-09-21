package com.skilldistillery.todoapp.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Todo> index(String username) {
		return todoRepo.findByUser_Username(username);
	}

	@Override
	public Todo show(String username, int tid) {
		Todo todo = todoRepo.findByIdAndUserUsername(tid, username);
		return todo;
	}

	@Override
	public Todo create(String username, Todo todo) {
		User user = userRepo.findByUsername(username);
		todo.setUser(user);
		todoRepo.saveAndFlush(todo);
		
		return todo;
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		Todo managedTodo = todoRepo.findByIdAndUserUsername(tid, username);
		if(managedTodo != null) {
			managedTodo.setTask(todo.getTask());
			managedTodo.setDescription(todo.getDescription());
			managedTodo.setCompleted(todo.getCompleted());
			managedTodo.setDueDate(todo.getDueDate());
			managedTodo.setCompleteDate(todo.getCompleteDate());
			todoRepo.saveAndFlush(managedTodo);
		}
		return managedTodo;
	}

	@Override
	public boolean destroy(String username, int tid) {
		boolean deleted = false;
		Todo managedTodo = todoRepo.findByIdAndUserUsername(tid, username);
		if(managedTodo != null) {
			todoRepo.delete(managedTodo);
			deleted = true;
		}
		return deleted;
	}
	



}
