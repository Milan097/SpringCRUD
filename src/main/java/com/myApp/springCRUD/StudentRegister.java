package com.myApp.springCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentRegister extends SpringBootServletInitializer {
	// This is new Version
	// This is new Developement Branch
	public static void main(String[] args) {
		System.out.println("Development Branch -> Push + Tags");
		System.out.println("Tags");
		SpringApplication.run(StudentRegister.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<StudentRegister> applicationClass = StudentRegister.class;
	
}
