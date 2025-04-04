package com.bullet.sqlite3.dao;

import com.bullet.employee.strategy.*;
import com.bullet.person.MyDate;
import com.bullet.sqlite3.model.Employee;
import com.bullet.sqlite3.model.EmployeeFactory;
import com.bullet.sqlite3.model.EmployeePaymentDetails;
import com.bullet.sqlite3.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.bullet.employee.strategy.PaymentType.*;

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
                     "hourlyRate DECIMAL," +
                     "standardHours DECIMAL," +
                     "dailyRate DECIMAL," +
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
                     "hourlyRate," +
                     "standardHours," +
                     "dailyRate," +
                     "standardDays," +
                     "monthlySalary)" +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employeePaymentDetails.getEmployeeNumber());
            ps.setString(2, employeePaymentDetails.getDepartment());
            ps.setString(3, employeePaymentDetails.getJobTitle());
            ps.setString(4, employeePaymentDetails.getDateJoined().toString());
            ps.setString(5, employeePaymentDetails.getPaymentType().name());
            ps.setFloat(6, employeePaymentDetails.getHourlyRate());
            ps.setFloat(7, employeePaymentDetails.getStandardHours());
            ps.setFloat(8, employeePaymentDetails.getDailyRate());
            ps.setInt(9, employeePaymentDetails.getStandardDays());
            ps.setFloat(10, employeePaymentDetails.getMonthlySalary());


            ps.executeUpdate();




        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

    }

    @Override
    public EmployeePaymentDetails getEmployeePaymentDetailsByEmployeeNumber(String employeeNumber) {
        String sql = "SELECT e.employeeNumber, e.firstname, e.lastname, " +
                "pd.employeeDepartment, pd.jobTitle, pd.dateJoined, pd.paymentType, " +
                "pd.hourlyRate, " +
                "pd.standardHours, pd.dailyRate, " +
                "pd.standardDays, pd.monthlySalary " +
                "FROM employees e " +
                "LEFT JOIN paymentDetails pd " + /* LEFT JOIN ensures that we get the employee
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

                // get the date joined
                String[] date = rs.getString(6).split("-");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                employeePaymentDetails.setDateJoined(MyDate.create(year, month, day));
                PaymentType paymentType = PaymentType.valueOf(rs.getString(7));
                employeePaymentDetails.setPaymentType(paymentType);


                employeePaymentDetails.setPaymentTypeStrategy(createPaymentTypeStrategy(paymentType));//8
                employeePaymentDetails.setHourlyRate(rs.getFloat(8));
                employeePaymentDetails.setStandardHours(rs.getFloat(9));
                employeePaymentDetails.setDailyRate(rs.getFloat(10));
                employeePaymentDetails.setStandardDays(rs.getInt(11));
                employeePaymentDetails.setMonthlySalary(rs.getFloat(12));

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
        //String sql = "SELECT * FROM paymentDetails";
        String sql = "SELECT e.employeeNumber, e.firstname, e.lastname, " +
                "pd.employeeDepartment, pd.jobTitle, pd.dateJoined, pd.paymentType, " +
                "pd.hourlyRate, " +
                "pd.standardHours, pd.dailyRate, " +
                "pd.standardDays, pd.monthlySalary " +
                "FROM employees e " +
                "INNER JOIN paymentDetails pd " +
                "ON e.employeeNumber = pd.employeeNumber " +
                "WHERE e.employeeNumber = pd.employeeNumber";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeePaymentDetails epd = new EmployeePaymentDetails();
                String employeeNumber = rs.getString(1);
                epd.setEmployeeNumber(employeeNumber);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                Person person = new Person(firstname, lastname);
                Employee employee = EmployeeFactory.existingEmployee(person, employeeNumber);
                epd.setDepartment(rs.getString(4));
                epd.setJobTitle(rs.getString(5));

                String date = rs.getString(6);
                String[] dateParts = date.split("-");
                //MyDate dateJoined = new MyDate(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                //epd.setDateJoined(dateJoined);
                PaymentType paymentType = PaymentType.valueOf(rs.getString(7));
                epd.setPaymentType(paymentType);
                PaymentTypeStrategy strategy = createPaymentTypeStrategy(paymentType);
                epd.setPaymentTypeStrategy(strategy);
                epd.setHourlyRate(rs.getFloat(8));
                epd.setStandardHours(rs.getFloat(9));
                epd.setDailyRate(rs.getFloat(10));
                epd.setStandardDays(rs.getInt(11));
                epd.setMonthlySalary(rs.getFloat(12));
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
    private PaymentTypeStrategy createPaymentTypeStrategy(PaymentType paymentType) {
        PaymentTypeStrategy pts = null;
        if (paymentType == MONTHLY) {
            pts= new MonthlySalaryStrategy();
        } else if (paymentType == HOURLY) {
            pts = new HourlySalaryStrategy();
        } else if (paymentType == DAILY) {
            pts = new DailySalaryStrategy();
        }
        return pts;

    }
}
