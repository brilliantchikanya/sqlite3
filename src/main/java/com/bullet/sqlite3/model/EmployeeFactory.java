package com.bullet.sqlite3.model;

import com.bullet.person.Person;

public class EmployeeFactory {
    private static long emp_number;


    public static Employee newEmployee(/*long employeeID, */Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person must be provided");
        }
        String employeeNumber = generateEmploymentNumber();
        return new Employee(/*employeeID, */person, employeeNumber);
    }

    public static Employee existingEmployee(/*long employeeID, */Person person, String employeeNumber) {
        if (person == null || employeeNumber == null || employeeNumber.isEmpty()) throw new IllegalArgumentException("Person and employee number must be provided");
        return new Employee(/*employeeID, */person, employeeNumber);

    }

    private static String generateEmploymentNumber() {
        String prefix = "BSC";
        if (emp_number < 10) {
            return prefix + "000" + ++emp_number;
        }
        else if (emp_number == 10) {
            return prefix +"0010" + ++emp_number;
        }
        else if (emp_number < 100) {
            return prefix + "00" + ++emp_number;
        } else if (emp_number == 100) {
            return prefix + "0100";
        } else if (emp_number < 1000) {
            return prefix + "0" + ++emp_number;
        } else if (emp_number < 10000) {
            return prefix + ++emp_number;
        }
        return prefix + ++emp_number;
        //return prefix + emp_number;

    }

}
