package com.bullet.sqlite3.dao;

import com.bullet.employee.Employee;
import static com.bullet.person.Gender.MALE;

import java.sql.SQLException;

public class TESTDB {
    public static void main(String[] args) throws SQLException {
        // this creates the database for our application
        EmployeeDAOImpl data = new EmployeeDAOImpl();
        Employee employee = new Employee();
        employee.setEmployeeFirstName("bullet");
        employee.setEmployeeLastName("chiks");
        employee.setEmployeeNumber();
        employee.setEmployeeGender(MALE);
        System.out.println(employee.getEmployeeNumber());

        // Save an employee in the database
        data.saveEmployee(employee);

        // retrieve an employee from the database
        Employee retrievedEmployee = data.getEmployeeById(7);

        if (retrievedEmployee != null) {

            System.out.println("Employee Number: " + retrievedEmployee.getEmployeeNumber());
            System.out.println("First Name: " + retrievedEmployee.getEmployeeFirstName());
            System.out.println("Last Name: " + retrievedEmployee.getEmployeeLastName());
            System.out.println("Gender: " + retrievedEmployee.getEmployeeGender().name());
            System.out.println("ID: " + retrievedEmployee.getEmployeeID());
        }
        else {
            System.out.println("The user does not exist...");
        }


    }
}

/****

 .idea/.gitignore
 .idea/encodings.xml
 .idea/misc.xml
 .idea/vcs.xml
 .mvn/wrapper/maven-wrapper.jar
 .mvn/wrapper/maven-wrapper.properties
 mvnw
 mvnw.cmd

 */
