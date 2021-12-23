package com.example.springbootmongo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.example.springbootmongo.exception.TodoCollectionException;
import com.example.springbootmongo.model.TodoDTO;

public interface TodoService {

	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;
	
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
	
	public void deleteById(String id) throws TodoCollectionException;
}
