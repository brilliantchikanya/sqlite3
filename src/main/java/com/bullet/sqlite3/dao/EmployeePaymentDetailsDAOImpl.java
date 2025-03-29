package com.bullet.sqlite3.dao;

import com.bullet.employee.strategy.EmployeePaymentDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/******     PAYMENT DETAILS TABLE       **/
public class EmployeePaymentDetailsDAOImpl implements EmployeePaymentDetailsDAO {
    public EmployeePaymentDetailsDAOImpl() {
        String sql = "CREATE TABLE IF NOT EXISTS paymentDetails (" +
                     "payrollID INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "employeeID INTEGER NOT NULL," +
                     "employeeDepartment TEXT," +
                     "jobTitle TEXT," +
                     "dateJoined TEXT," +
                     "branch TEXT," +
                     "grade TEXT," +
                     "currency TEXT," +
                     "paymentType TEXT," +
                     "paymentTypeStrategy TEXT," +
                     "hourlyRate DECIMAL," +
                     "hoursWorked DECIMAL," +
                     "standardHours DECIMAL," +
                     "dailyRate DECIMAL," +
                     "daysWorked INTEGER," +
                     "standardDays INTEGER," +
                     "basicSalary DECIMAL," +
                     "payrollDate TEXT" +
                     ")";

        try (Connection conn = SQLITEDB.getConnection();
             Statement statement = conn.createStatement()) {
            boolean result = statement.execute(sql);
            if (result) System.out.println("created payment details table successfully");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating table");
        }
    }

    @Override
    public void saveEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails) {
        String sql = "INSERT INTO paymentDetails (employeeID, employeeDepartment," +
                     "jobTitle," +
                     "dateJoined," +
                     "branch," +
                     "grade," +
                     "currency," +
                     "paymentType," +
                     "paymentTypeStrategy," +
                     "hourlyRate," +
                     "hoursWorked," +
                     "standardHours," +
                     "dailyRate," +
                     "daysWorked," +
                     "standardDays," +
                     "basicSalary," +
                     "payrollDate)" +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            //logic here
            ps.setInt(1, (int) employeePaymentDetails.getEmployeeID());
            ps.setString(2, employeePaymentDetails.getDepartment());
            ps.setString(3, employeePaymentDetails.getJobTitle());
            ps.setString(4, employeePaymentDetails.getDateJoined().toString());
            ps.setString(5, employeePaymentDetails.getBranch());
            ps.setString(6, employeePaymentDetails.getGrade().name());
            ps.setString(7, employeePaymentDetails.getCurrency().name());
            ps.setString(8, employeePaymentDetails.getPaymentType().name());
            ps.setString(9, employeePaymentDetails.getPaymentTypeStrategy().toString());
            ps.setFloat(10, employeePaymentDetails.getHourlyRate());
            ps.setFloat(11, employeePaymentDetails.getHoursWorked());
            ps.setFloat(12, employeePaymentDetails.getStandardHours());
            ps.setFloat(13, employeePaymentDetails.getDailyRate());
            ps.setInt(14, employeePaymentDetails.getDaysWorked());
            ps.setInt(15, employeePaymentDetails.getStandardDays());
            ps.setFloat(16, (float) employeePaymentDetails.getBasicSalary());
            ps.setString(17, employeePaymentDetails.getPayrollDate().toString());


            ps.executeUpdate();
            // get generated key
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                employeePaymentDetails.setPayrollID(rs.getInt(1));
            }
        }
        catch (Exception e) {
           e.printStackTrace();
            System.out.println("Could not save payment details record for " +
                    employeePaymentDetails.getEmployee().getEmployeeFirstName());
        }

    }

    @Override
    public EmployeePaymentDetails getEmployeePaymentDetailsById(int payrollID) {
        return null;
    }

    @Override
    public List<EmployeePaymentDetails> getAllEmployeePaymentDetails() {
        return List.of();
    }

    @Override
    public void updateEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails) {

    }

    @Override
    public void deleteEmployeePaymentDetails(int payrollID) {

    }
}
