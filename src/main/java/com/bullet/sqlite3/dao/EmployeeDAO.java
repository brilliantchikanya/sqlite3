package com.bullet.sqlite3.dao;

import com.bullet.sqlite3.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void saveEmployee(Employee employee);
    Employee getEmployeeByEmployeeNumber(String employeeNumber);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(String employeeNumber);

}
