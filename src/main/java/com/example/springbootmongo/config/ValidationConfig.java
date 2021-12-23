package com.example.springbootmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

	//This bean will check the user inputs and return error on any invalid inputs like null
	@Bean
	public ValidatingMongoEventListener validationMongoEventListner(){
		return new ValidatingMongoEventListener(validator());
	}
	
	@Bean
	public 	LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
