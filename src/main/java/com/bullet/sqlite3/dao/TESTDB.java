package com.bullet.sqlite3.dao;

import com.bullet.employee.Employee;
import com.bullet.employee.strategy.EmployeePaymentDetails;
import com.bullet.employee.strategy.HourlySalaryStrategy;
import com.bullet.person.MyDate;

import static com.bullet.employee.strategy.Currency.USD;
import static com.bullet.employee.strategy.EmployeeGrade.ONE;
import static com.bullet.employee.strategy.PaymentType.HOURLY;
import static com.bullet.person.Gender.FEMALE;
import static com.bullet.person.Gender.MALE;

import java.sql.SQLException;

public class TESTDB {
    public static void main(String[] args) throws SQLException {
        // this creates the database for our application
        EmployeeDAOImpl data = new EmployeeDAOImpl();
        EmployeePaymentDetailsDAOImpl paymentDetails = new EmployeePaymentDetailsDAOImpl();
        Employee employee = new Employee();
        employee.setEmployeeFirstName("bullet");
        employee.setEmployeeLastName("chiks");
        //employee.setEmployeeNumber();
        employee.setEmployeeGender(MALE);
        //System.out.println(employee.getEmployeeNumber());

        // Save an employee in the database
        data.saveEmployee(employee);

        // retrieve an employee from the database
        Employee retrievedEmployee = data.getEmployeeById(1);

        if (retrievedEmployee != null) {

            //System.out.println("Employee Number: " + retrievedEmployee.getEmployeeNumber());
            System.out.println("First Name: " + retrievedEmployee.getEmployeeFirstName());
            System.out.println("Last Name: " + retrievedEmployee.getEmployeeLastName());
            System.out.println("Gender: " + retrievedEmployee.getEmployeeGender().name());
            System.out.println("ID: " + retrievedEmployee.getEmployeeID());
        }
        else {
            System.out.println("The user does not exist...");
        }
        if (retrievedEmployee != null) {
            retrievedEmployee.setEmployeeGender(FEMALE);
        }

        if (retrievedEmployee != null) {
            data.updateEmployee(retrievedEmployee);
        }


        // delete the first employee
        data.deleteEmployee(2);


        /************   PAYMENT DETAILS SECTION     *****/

        EmployeePaymentDetails employeePaymentDetails = new EmployeePaymentDetails(employee);
        employeePaymentDetails.setPayrollID(1);
        employeePaymentDetails.setPaymentTypeStrategy(new HourlySalaryStrategy());
        employeePaymentDetails.setPaymentType(HOURLY);
        employeePaymentDetails.setJobTitle("ADMIN");
        employeePaymentDetails.setDepartment("DIRECTORS");
        employeePaymentDetails.setEmployeeID(employee.getEmployeeID());
        employeePaymentDetails.setDateJoined(new MyDate(2020, 11, 15));
        employeePaymentDetails.setPayrollDate(new MyDate(2025, 03, 31));
        employeePaymentDetails.setGrade(ONE);
        employeePaymentDetails.setCurrency(USD);
        paymentDetails.saveEmployeePaymentDetails(employeePaymentDetails);

        /************   END PAYMENT DETAILS SECTION     *****/
    }
}

