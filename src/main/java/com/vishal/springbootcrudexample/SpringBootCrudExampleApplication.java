package com.vishal.springbootcrudexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is used to mark a configuration class that declares one
// or more @Bean methods and also triggers
//auto-configuration and component scanning
@SpringBootApplication
public class SpringBootCrudExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExampleApplication.class, args);
	}

}
