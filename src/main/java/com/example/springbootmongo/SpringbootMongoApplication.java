package com.example.springbootmongo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@SpringBootApplication
public class SpringbootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoApplication.class, args);
	}
	
	
	
	//for changing swagger information 
	@Bean
	public OpenAPI openApiConfig()
	{
		return new OpenAPI().info(apiConfig());
	}

	
	public Info apiConfig() {
		Info info = new Info();
		info.title("SPRINGBOOT MONGO CRUD API").description("A Simple CRUD API using Spring Boot and MongoDB").version("v1.0.0");
		return info;
	}
	
	

}
