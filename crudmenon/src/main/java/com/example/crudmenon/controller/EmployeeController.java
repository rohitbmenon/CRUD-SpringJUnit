package com.example.crudmenon.controller;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudmenon.model.Employee;
import com.example.crudmenon.repo.EmployeeRepo;

@RestController // This annotation will tell Spring that this class will be handling/generating the HTTP requests(class with REST endpoints)
public class EmployeeController {
    
    @Autowired // Will inject the EmployeeRepository bean to the local variable
    private EmployeeRepo employeeRepo;




    @GetMapping ("/Employees") //Retrieve all employees
    // Won't be void because when creating RESTful web services we have to return something back which is where we are creating ResponseEntity = (which is a class, represents entire HTTP response, gives more custiomzation to that response)<List<Employee>>, getting the list of employees
    // Using List<> to return that list of employees and details, easier to convert to Java objects into JSON formart
    public ResponseEntity<List<Employee>> getAllEmployees() {
       
        try   {

            //Creating new Array list and the using JPA method findAll, we are showing the employees in the database
            List<Employee> employeeList = new ArrayList<>();
            employeeRepo.findAll().forEach(employeeList::add);


            if (employeeList.isEmpty()) { //Saying, if content is empty and there is no data, the status code no content will appear for the client 
                return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
            }

            //Status code for if everything is correctly coming and there is data to be shown
            return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR); // If there are any errors, will tell client there was an error with the server and can't provide the details

        }

    }

    @GetMapping ("/Employees/{id}") //Get details of a specfic employee by their ID, which where we use @PathVariable 
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable Long id) {

      Optional<Employee> employeeData = employeeRepo.findById(id); // Handles scenarios where it could be null or could be present
      
      if (employeeData.isPresent()) {
        return new ResponseEntity<>(employeeData.get(),HttpStatus.OK); //Using .get() method to acutally get the data
      }

      return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);

    }



    @PostMapping ("/Employees") //Creating a new employee
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        
       try {

         Employee employeeObj = employeeRepo.save(employee); //save the object we want to the database, in the parameters we are returning eveything but the id, but once creating this object employeeObj we are returning everything included the autogenerted ID
         return new ResponseEntity<>(employeeObj, HttpStatus.OK);

       } catch (DateTimeParseException e) {

        System.out.println("Invalid date input: " + e.getMessage());
       }
         return new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);      
    }



    @PutMapping  ("/Employees/{id}") //Updating employee details by ID
    public ResponseEntity<Employee> updateEmployee (@PathVariable Long id, @RequestBody Employee newEmployeeData) {

       Optional<Employee> oldEmployeeData = employeeRepo.findById(id);
        
       if (oldEmployeeData.isPresent()) {

        Employee updatedEmployeeData = oldEmployeeData.get();
        updatedEmployeeData.setFirstName(newEmployeeData.getFirstName());
        updatedEmployeeData.setLastName(newEmployeeData.getLastName());
        updatedEmployeeData.setEmailAddress(newEmployeeData.getEmailAddress());
        updatedEmployeeData.setPhone(newEmployeeData.getPhone());
        updatedEmployeeData.setBirthDate(newEmployeeData.getBirthDate());
        updatedEmployeeData.setJobTitle(newEmployeeData.getJobTitle());
        updatedEmployeeData.setDepartment(newEmployeeData.getDepartment());
        updatedEmployeeData.setLocation(newEmployeeData.getLocation());
        updatedEmployeeData.setStartDate(newEmployeeData.getStartDate());
        updatedEmployeeData.setManagerReporting(newEmployeeData.getManagerReporting());

        Employee employeeObj = employeeRepo.save(updatedEmployeeData);
        return new ResponseEntity<Employee>(employeeObj, HttpStatus.OK);

       }

       return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);

    }



    @DeleteMapping  ("/Employees/{id}") //Deleting an employee
    public ResponseEntity<HttpStatus> deleteEmployeeByID (@PathVariable Long id) {
    try {
        employeeRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    } catch (EmptyResultDataAccessException e) { // This exception is being used as in if the id entered is not found/valid
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch (Exception e) { // Any other expection 
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
