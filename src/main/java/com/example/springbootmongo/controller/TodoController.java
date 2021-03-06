package com.example.springbootmongo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmongo.exception.TodoCollectionException;
import com.example.springbootmongo.model.TodoDTO;
import com.example.springbootmongo.repository.TodoRepository;
import com.example.springbootmongo.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api")
@Tag(name="Todo") //for swagger
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todos")
	@Operation(summary = "Retreive all todos",
				responses= {
						@ApiResponse(description="Retrieve all todos", responseCode="200",
								content = @Content(mediaType="application/json", schema=@Schema(implementation = TodoDTO.class))),
						@ApiResponse(description="Todo Not Found ", responseCode="404", content = @Content)	
				})
	public ResponseEntity<?> getAllTodos()
	{
		List<TodoDTO> todos = todoService.getAllTodos();  
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/todos")
	@Operation(summary = "Create a new Todo")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo)
	{
		try {
			todoService.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.CREATED);
		}  
		catch(ConstraintViolationException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

		}
		
	}
	
	@GetMapping("/todo/{id}")
	@Operation(summary = "Retrieve a single todo using it's id")
	public ResponseEntity<?> getSingleTodo(@PathVariable String id){
		try {
			return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/todo/{id}")
	@Operation(summary = "Update a Todo")
	public ResponseEntity<?> updateTodo(@PathVariable String id, @RequestBody TodoDTO todo){
		try {
			todoService.updateTodo(id, todo);
			return new ResponseEntity<>("Todo "+id+" Updated Successully", HttpStatus.OK);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	}
	
	
	@DeleteMapping("/todo/{id}")
	@Operation(summary = "Delete a todo using id")
	public ResponseEntity<?> deleteById(@PathVariable String id){
		try {
			todoService.deleteById(id);
			return new ResponseEntity<>("Todo with id "+id+" Deleted Successfully", HttpStatus.OK);
		}catch(TodoCollectionException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		
	}
	
}
