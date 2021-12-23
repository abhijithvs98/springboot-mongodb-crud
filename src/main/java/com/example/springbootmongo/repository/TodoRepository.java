package com.example.springbootmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootmongo.model.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {
	@Query("{'todo':?0}") //?0 indicate the first parameter in the method
	Optional<TodoDTO> findByTodo(String todo);
}
