package com.example.crudmenon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// I created this Spring Boot Application by creating my project in Spring Initializr
// The dependencies (which can be located in the pom.xml file) I used for this project was first, Spring Web, where this will help create RESTful applications using Spring MVC
// The next one I used was Spring Data JPA, where this will help make is eaier to implement into JPA based repositories; helps locate java objects to tables in relational database
// The next one I used was H2 Database, where this will help connect to the in-memory database
// The last one I used was Lombok, where this will help me reduce boilerplate code (like getters/setters, toString and etc)

@SpringBootApplication
public class CrudmenonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudmenonApplication.class, args);
	}

}
