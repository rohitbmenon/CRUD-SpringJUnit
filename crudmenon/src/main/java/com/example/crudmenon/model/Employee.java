package com.example.crudmenon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Employees") //Tells which table to map to, where the deafult would be the same name as the class

//Using the java library Lombok, it will be reducing the boilerplate code when creating constructors, getters and setters and toString methods (at the compile-time)
//Instead of using these three annotations, I can use @Data which will get me the same values but for this example, I will be typing these 3 manually to be more specific
@Getter
@Setter
@ToString
public class Employee {
    
    //Creating the variables needed for an Employee
    //Using the "private" access modifer to achieve data hiding and flexibilty (Encapsulation)
    // @GeneratedValue(strategy = GenerationType.AUTO) // When creating a new employee, will generate an ID and will be able to return everything else, The reason I'm commenting this is to show the Junit Testing
    @Id
    private long id; // Employee ID, primary Key

    @Column(name = "First Name") // Creating column name for table by using this annonation 
    private String firstName; // First Name

    @Column(name = "Last Name")
    private String lastName; // Last Name
    
    @Column(name = "Email Id")
    private String emailAddress; // Email Address

    @Column(name = "Phone Number")
    private String phone; // Phone Number

    @Column(name = "Date of Birth")
    private String birthDate; // Birthday

    @Column(name = "Job Title")
    private String jobTitle; // Job Title

    @Column(name = "Department")
    private String department; // Department

    @Column(name = "Location")
    private String location; // Location
    
    @Column(name = "Start Date")
    private String startDate; // Start Date

    @Column(name = "Manager Reporting")
    private String managerReporting; // Manager Reporting


    //Creating constructors with no arg and with arg -- this could be achieved with also @AllArgsConstructor and @NoArgsConstructor from the Lombok library but for some reason was getting an error when using both, which made me create them manually

    public Employee() {

    }

    public Employee (String firstName, String lastName, String emailAddress, String phone, String birthDate, String jobTitle, String department, String location, String startDate, Long id, String managerReporting) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.department = department;
        this.location = location;
        this.startDate = startDate;
        this.id = id;
        this.managerReporting = managerReporting;
    }
}
