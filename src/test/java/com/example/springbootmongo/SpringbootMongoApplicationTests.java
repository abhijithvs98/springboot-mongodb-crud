package com.example.springbootmongo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springbootmongo.model.TodoDTO;
import com.example.springbootmongo.repository.TodoRepository;

@SpringBootTest
class SpringbootMongoApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	TodoRepository todoRepo;
	
	public void testCreate() {
		TodoDTO todo = new TodoDTO();
		todo.setId("test");
		todo.setTodo("Test TODO from testcase");
		todo.setDescription("test Description form testcase");
		todo.setCompleted(false);
		
		todoRepo.save(todo);
		
		assertNotNull(todoRepo.findById("test").get());
	}
	
}
