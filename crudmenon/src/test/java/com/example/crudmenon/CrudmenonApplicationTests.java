package com.example.crudmenon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.crudmenon.model.Employee;
import com.example.crudmenon.repo.EmployeeRepo;

@SpringBootTest
class CrudmenonApplicationTests {

@Autowired // Will inject the EmployeeRepository bean to the local variable
private EmployeeRepo employeeRepo; 


	@Test
	public void testCreateEmployee() throws ParseException{ //Using ParseExcpection to say there has been an error while parsing the test methods 
		Employee e = new Employee();
		e.setId(1);
		e.setFirstName("Roger");
		e.setLastName("Federer");
		e.setEmailAddress("rf@gmail.com");
		e.setDepartment("Tennis");
		e.setLocation("Spain");
		e.setManagerReporting("Tennis World");
		e.setBirthDate("2001-04-12");
		e.setPhone("301-795-5542");
		e.setStartDate("2020-01-01");
		e.setJobTitle("Tennis Player");
		employeeRepo.save(e);
		assertNotNull(employeeRepo.findById((long) 1).get()); // A Junit asseration where it checks if the value id is null which will lead to a failed test, but if not null test will pass
	}

	@Test
	public void testReadAllEmployee() throws ParseException{
		java.util.List<Employee> employees = employeeRepo.findAll();
		assertTrue(!employees.isEmpty()); // Checks if the list is not empty
		}

	@Test 
	public void testReadSingleEmployee() throws ParseException {
		Employee employee = employeeRepo.findById((long) 1).get();
		assertEquals("Roger", employee.getFirstName()); // A Junit asseration making sure to get the id and making sure if the value of the first name is the same
	}

	@Test
	public void testUpdateEmployee() throws ParseException{
		Employee e = employeeRepo.findById((long) 1).get();
		e.setFirstName("Nadal");
		employeeRepo.save(e);
		assertNotEquals("Roger", employeeRepo.findById((long) 1).get().getFirstName());
	}

	@Test
	public void testDeleteEmployee() throws ParseException {
		employeeRepo.deleteById((long) 1);
		assertFalse(employeeRepo.existsById((long) 1));
	}

}
