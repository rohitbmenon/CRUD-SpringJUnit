package com.example.crudmenon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudmenon.model.Employee;

//Creating a respository interface where we are extending JPA respository
//Defines CRUD operations and Query Methods
//Spring Boot has bulit-in support for repo interfaces; so will do the work for creating repo bean and injecting it into my controller
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
     
}
