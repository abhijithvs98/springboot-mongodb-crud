package com.example.springbootmongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springbootmongo.exception.TodoCollectionException;
import com.example.springbootmongo.model.TodoDTO;
import com.example.springbootmongo.repository.TodoRepository;

@Service
public class TodoServiceImp implements TodoService  {

	@Autowired
	private TodoRepository todoRepo;

	@Override
	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException {
	  Optional<TodoDTO> todoOptional = todoRepo.findByTodo(todo.getTodo());	
	  if(todoOptional.isPresent())
	  {
		  throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
	  }else {
		  todo.setCreatedAt(new Date(System.currentTimeMillis()));
		  todoRepo.save(todo);
	  }
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		List<TodoDTO> todos = todoRepo.findAll();  
		if(todos.size() > 0){
			return todos; 
		}else{
			return new ArrayList<TodoDTO>();
		}		
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> todo = todoRepo.findById(id);
		if(todo.isPresent()){
			return todo.get();
		}else{
			  throw new TodoCollectionException(TodoCollectionException.NotFondException(id));
		}
	}

	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
		Optional<TodoDTO> oldTodo = todoRepo.findById(id);
		Optional<TodoDTO> todoWithSameName = todoRepo.findByTodo(todo.getTodo());
		
		if(oldTodo.isPresent())
		{
			
			if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {
				throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
			}
			
			TodoDTO todoToUpdate = oldTodo.get();
			todoToUpdate.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToUpdate.getTodo());
			todoToUpdate.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToUpdate.getCompleted());
			todoToUpdate.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToUpdate.getDescription());
			todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todoToUpdate);
		}else{
				throw new TodoCollectionException(TodoCollectionException.NotFondException(id));
				
		}
	  }

	@Override
	public void deleteById(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoOptional = todoRepo.findById(id);
		if(todoOptional.isPresent()) {
			todoRepo.deleteById(id);
		}else {
			throw new TodoCollectionException(TodoCollectionException.NotFondException(id));
		}
			
	}

	

	
	
}
