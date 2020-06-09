package com.myApp.springCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentRegister {

	public static void main(String[] args) {
		SpringApplication.run(StudentRegister.class, args);
	}
	
}
