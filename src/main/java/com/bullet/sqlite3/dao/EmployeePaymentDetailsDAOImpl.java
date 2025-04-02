package com.bullet.sqlite3.dao;

import com.bullet.employee.strategy.HourlySalaryStrategy;
import com.bullet.employee.strategy.PaymentType;
import com.bullet.person.MyDate;
import com.bullet.sqlite3.model.Employee;
import com.bullet.sqlite3.model.EmployeeFactory;
import com.bullet.sqlite3.model.EmployeePaymentDetails;
import com.bullet.sqlite3.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/******     PAYMENT DETAILS TABLE       **/
/*
        This class handles all the contractual terms and agreement
        between the employer and the employee, as it relates to
        how the employee earnings are determined.
    *
    * */
public class EmployeePaymentDetailsDAOImpl implements EmployeePaymentDetailsDAO {
    public EmployeePaymentDetailsDAOImpl() {
        String sql = "CREATE TABLE IF NOT EXISTS paymentDetails (" +
                     "employeeNumber TEXT PRIMARY KEY," +
                     "employeeDepartment TEXT," +
                     "jobTitle TEXT," +
                     "dateJoined TEXT," +

/*                   "branch TEXT," +
                     "grade TEXT," +
                     "currency TEXT," +             */

                     "paymentType TEXT," +
                     "paymentTypeStrategy TEXT," +
                     "hourlyRate DECIMAL," +
                     "hoursWorked DECIMAL," +
                     "standardHours DECIMAL," +
                     "dailyRate DECIMAL," +
                     "daysWorked INTEGER," +
                     "standardDays INTEGER," +
                     "monthlySalary DECIMAL," +
                     "FOREIGN KEY (employeeNumber) REFERENCES employees(employeeNumber)" +
                     ")";

        try (Connection conn = SQLITEDB.getConnection();
             Statement statement = conn.createStatement()) {
            boolean result = statement.execute(sql);
            if (result) System.out.println("created payment details table successfully");

        }
        catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error creating table");
        }
    }

    @Override
    public void saveEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails) {
        String sql = "INSERT INTO paymentDetails (employeeNumber, employeeDepartment," +
                     "jobTitle," +
                     "dateJoined," +
                 /*    "branch," +
                     "grade," +
                     "currency," +          */
                     "paymentType," +
                     "paymentTypeStrategy," +
                     "hourlyRate," +
                     "hoursWorked," +
                     "standardHours," +
                     "dailyRate," +
                     "daysWorked," +
                     "standardDays," +
                     "monthlySalary)" +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employeePaymentDetails.getEmployeeNumber());
            ps.setString(2, employeePaymentDetails.getDepartment());
            ps.setString(3, employeePaymentDetails.getJobTitle());
            ps.setString(4, employeePaymentDetails.getDateJoined().toString());
            ps.setString(5, employeePaymentDetails.getPaymentType().name());
            ps.setString(6, employeePaymentDetails.getPaymentTypeStrategy().toString());
            ps.setFloat(7, employeePaymentDetails.getHourlyRate());
            ps.setFloat(8, employeePaymentDetails.getHoursWorked());
            ps.setFloat(9, employeePaymentDetails.getStandardHours());
            ps.setFloat(10, employeePaymentDetails.getDailyRate());
            ps.setInt(11, employeePaymentDetails.getDaysWorked());
            ps.setInt(12, employeePaymentDetails.getStandardDays());
            ps.setFloat(13, (float) employeePaymentDetails.getMonthlySalary());


            ps.executeUpdate();




        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            //System.out.println("Could not save payment details record for " +
              //      employeePaymentDetails.getEmployee().getEmployeeFirstName());
        }

    }

    @Override
    public EmployeePaymentDetails getEmployeePaymentDetailsByEmployeeNumber(String employeeNumber) {
        String sql = "SELECT e.employeeNumber, e.firstname, e.lastname, " +
                "pd.employeeDepartment, pd.jobTitle, pd.paymentType, " +
                "pd.paymentTypeStrategy, pd.hourlyRate, pd.hoursWorked, " +
                "pd.standardHours, pd.dailyRate, pd.daysWorked, " +
                "pd.standardDays, pd.monthlySalary " +
                "FROM employees e " +
                "INNER JOIN paymentDetails pd " + /* LEFT JOIN ensures that we get the employee
                                                     even if no employee details exist.

                             INNER JOIN is used if payment details are mandatory

                */
                "ON e.employeeNumber = pd.employeeNumber " +
                "WHERE e.employeeNumber = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employeeNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Employee = EmployeeFactory()
                EmployeePaymentDetails employeePaymentDetails = new EmployeePaymentDetails();
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                Person person = new Person(firstname, lastname);
                Employee employee = EmployeeFactory.existingEmployee(person, employeeNumber);
                employeePaymentDetails.setEmployeeNumber(employeeNumber);
                employeePaymentDetails.setDepartment(rs.getString(4));
                employeePaymentDetails.setJobTitle(rs.getString(5));
                employeePaymentDetails.setPaymentType(PaymentType.valueOf(rs.getString(6)));
                //employeePaymentDetails.setPaymentTypeStrategy(HourlySalaryStrategy);
                employeePaymentDetails.setHourlyRate(rs.getFloat(8));
                employeePaymentDetails.setHoursWorked(rs.getFloat(9));
                employeePaymentDetails.setStandardHours(rs.getFloat(10));
                employeePaymentDetails.setDailyRate(rs.getFloat(11));
                employeePaymentDetails.setDaysWorked(rs.getInt(12));
                employeePaymentDetails.setStandardDays(rs.getInt(13));
                employeePaymentDetails.setMonthlySalary(rs.getFloat(14));

                return employeePaymentDetails;
            }

        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<EmployeePaymentDetails> getAllEmployeePaymentDetails() {
        List<EmployeePaymentDetails> employeePaymentDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM paymentDetails";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeePaymentDetails epd = new EmployeePaymentDetails();
                epd.setEmployeeNumber(rs.getString(1));
                epd.setDepartment(rs.getString(2));
                epd.setJobTitle(rs.getString(3));

                String date = rs.getString(4);
                String[] dateParts = date.split("-");
                MyDate dateJoined = new MyDate(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                epd.setDateJoined(dateJoined);

                epd.setPaymentType(PaymentType.valueOf(rs.getString(5)));
                //employeePaymentDetails.setPaymentTypeStrategy(HourlySalaryStrategy);
                epd.setHourlyRate(rs.getFloat(7));
                epd.setHoursWorked(rs.getFloat(8));
                epd.setStandardHours(rs.getFloat(9));
                epd.setDailyRate(rs.getFloat(10));
                epd.setDaysWorked(rs.getInt(11));
                epd.setStandardDays(rs.getInt(12));
                epd.setMonthlySalary(rs.getFloat(13));
                employeePaymentDetailsList.add(epd);

            }


            return employeePaymentDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateEmployeePaymentDetails(EmployeePaymentDetails employeePaymentDetails) {

    }

    @Override
    public void deleteEmployeePaymentDetails(String employeeNumber) {
        String sql = "DELETE FROM paymentDetails WHERE employeeNumber = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employeeNumber);

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Details deleted successfully");
            }

        } catch (Exception e) {
           e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Details could not be deleted successfully");

        }

    }
}
