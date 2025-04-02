package com.bullet.sqlite3.model;



public class EmployeeFactory {

    private static long emp_number;


    public static Employee newEmployee(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person must be provided");
        }
        String employeeNumber = generateEmploymentNumber();
        return new Employee(person, employeeNumber);
    }

    public static Employee existingEmployee(Person person, String employeeNumber) {
        if (person == null || employeeNumber == null || employeeNumber.isEmpty()) throw new IllegalArgumentException("Person and employee number must be provided");
        return new Employee(person, employeeNumber);

    }

    private static String generateEmploymentNumber() {
        String prefix = "BSC";
        if (emp_number == 9) {
            ++emp_number;
            return prefix + "0010";
        } else if (emp_number < 10) {
            return prefix + "000" + ++emp_number;
        }  else if (emp_number == 99) {
            ++emp_number;
            return prefix + "0100";

        } else if (emp_number < 100) {
            return prefix + "00" + ++emp_number;
        } else if (emp_number == 999) {
            ++emp_number;
            return prefix + "1000";
        } else if (emp_number < 1000) {
            return prefix + "0" + ++emp_number;

        } else if (emp_number < 10000) {
            return prefix + ++emp_number;
        }
        return prefix + ++emp_number;

    }



}
