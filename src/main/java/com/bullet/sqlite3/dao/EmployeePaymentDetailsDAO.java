package com.bullet.sqlite3.dao;

import com.bullet.employee.strategy.EmployeePaymentDetails;

import java.util.List;

public interface EmployeePaymentDetailsDAO {
    void saveEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails);
    EmployeePaymentDetails getEmployeePaymentDetailsById(int payrollID);
    List<EmployeePaymentDetails> getAllEmployeePaymentDetails();
    void updateEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails);
    void deleteEmployeePaymentDetails(int payrollID);

}
