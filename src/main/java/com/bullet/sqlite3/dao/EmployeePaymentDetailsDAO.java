package com.bullet.sqlite3.dao;

import com.bullet.sqlite3.model.EmployeePaymentDetails;

import java.util.List;

public interface EmployeePaymentDetailsDAO {
    void saveEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails);
    EmployeePaymentDetails getEmployeePaymentDetailsByEmployeeNumber(String employeeNumber);
    List<EmployeePaymentDetails> getAllEmployeePaymentDetails();
    void updateEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails);
    void deleteEmployeePaymentDetails(String employeeNumber);

}
