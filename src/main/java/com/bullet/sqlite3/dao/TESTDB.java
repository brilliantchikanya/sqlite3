package com.bullet.sqlite3.dao;

//import com.bullet.person.Person;
import com.bullet.employee.strategy.HourlySalaryStrategy;
import com.bullet.person.MyDate;
import com.bullet.sqlite3.model.Employee;
import com.bullet.sqlite3.model.EmployeeFactory;
import com.bullet.sqlite3.model.EmployeePaymentDetails;
import com.bullet.sqlite3.model.Person;

import static com.bullet.employee.strategy.PaymentType.HOURLY;
import static com.bullet.person.Gender.FEMALE;
import static com.bullet.person.Gender.MALE;

import java.sql.SQLException;
import java.util.List;

public class TESTDB {
    public static void main(String[] args) throws SQLException {
        // this creates the database for our application
        EmployeeDAOImpl data = new EmployeeDAOImpl();
        EmployeePaymentDetailsDAOImpl paymentDetails = new EmployeePaymentDetailsDAOImpl();
        //Employee employee = new Employee();
        String firstname = "bullet";
        String lastname = "chiks";
        Person person = new Person(firstname, lastname);
        Employee employee = EmployeeFactory.newEmployee(person);
        //System.out.println(employee);
        employee.getPerson().setGender(MALE);
        //System.out.println(employee.getEmployeeNumber());


        //System.out.println(employee.getEmployeeNumber());

        // Save an employee in the database
        data.saveEmployee(employee);

        // retrieve an employee from the database
        Employee retrievedEmployee = data.getEmployeeByEmployeeNumber("BSC0001");

        if (retrievedEmployee != null) {

            //System.out.println("Employee Number: " + retrievedEmployee.getEmployeeNumber());
            System.out.println("First Name: " + retrievedEmployee.getPerson().getName().getFirstName());
            System.out.println("Last Name: " + retrievedEmployee.getPerson().getName().getLastName());
            System.out.println("Gender: " + retrievedEmployee.getPerson().getGender().name());
            //System.out.println("ID: " + retrievedEmployee.getEmployeeID());
        }
        else {
            System.out.println("The user does not exist...");
        }
        if (retrievedEmployee != null) {
            retrievedEmployee.getPerson().setGender(FEMALE);
        }

        if (retrievedEmployee != null) {
            data.updateEmployee(retrievedEmployee);
        }
        System.out.println("/************   PRINTING OUT EMPLOYEE INFORMATION   ******/");
        List<Employee> employeeList = data.getAllEmployees();
        for (Employee employee1: employeeList) {
            System.out.println("################################################");
            System.out.println();
            System.out.println("Employee Number: " + employee1.getEmployeeNumber());
            System.out.println("First Name:      " + employee1.getPerson().getName().getFirstName());
            System.out.println("Last Name:       " + employee1.getPerson().getName().getLastName());
            System.out.println("Gender:          " + employee1.getPerson().getGender().name());
            System.out.println();
            System.out.println("################################################");
        }


        System.out.println("/************   END OF EMPLOYEE INFORMATION   ******/");



        // delete the first employee
        //data.deleteEmployee("BSC0001");


        /************   PAYMENT DETAILS SECTION     *****/

        EmployeePaymentDetails employeePaymentDetails = new EmployeePaymentDetails();
        System.out.println(employee.getEmployeeNumber());
        employeePaymentDetails.setEmployeeNumber(employee.getEmployeeNumber());
        employeePaymentDetails.setPaymentType(HOURLY);
        employeePaymentDetails.setPaymentTypeStrategy(new HourlySalaryStrategy());
        employeePaymentDetails.setJobTitle("ADMIN");
        employeePaymentDetails.setDepartment("DIRECTORS");
        employeePaymentDetails.setDateJoined(MyDate.create(2020, 11, 15));
        paymentDetails.saveEmployeePaymentDetails(employeePaymentDetails);

        EmployeePaymentDetails pd = paymentDetails.getEmployeePaymentDetailsByEmployeeNumber(employeePaymentDetails.getEmployeeNumber());
        System.out.println(pd);
        List<EmployeePaymentDetails> epdList = paymentDetails.getAllEmployeePaymentDetails();
        System.out.println(epdList);

        /************   END PAYMENT DETAILS SECTION     *****/
    }
}

