package com.bullet.sqlite3.dao;

import com.bullet.person.Person;
import com.bullet.sqlite3.model.Employee;
import com.bullet.person.Gender;
import com.bullet.sqlite3.model.EmployeeFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{

    public EmployeeDAOImpl() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                     "employeeNumber TEXT UNIQUE," +
                     "employeeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "firstname TEXT NOT NULL," +
                     "lastname TEXT NOT NULL," +
//TODO needs relook                     "employeeNumber TEXT," +
                     "employeeGender TEXT" +
                     ")";

        try (Connection conn = SQLITEDB.getConnection();
            Statement statement = conn.createStatement()) {
            statement.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employees (employeeNumber, firstname, lastname," +
                     "employeeGender) VALUES (?,?,?,?)";

        try (Connection conn = SQLITEDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql,
                                    PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, employee.getEmployeeNumber());

            ps.setString(2, employee.getPerson().getFirstName());
            ps.setString(3, employee.getPerson().getLastName());
            //ps.setString(3, employee.getEmployeeNumber());
            ps.setString(4, employee.getPerson().getGender().name());
            ps.executeUpdate();
            // Get the generated ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //employee.setEmployeeID(rs.getInt(1));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Employee getEmployeeByEmployeeNumber(String employeeNumber) {
        String sql = "SELECT * FROM employees WHERE employeeNumber = ?";
        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employeeNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //long employeeID = (long) rs.getInt(1);
                String firstname = rs.getString(3);
                String lastname = rs.getString(4);
//                String employeeNumber = rs.getString(4);
                String gender = rs.getString(5);
                Gender employeeGender = Gender.valueOf(gender);
                Person person = new Person(firstname, lastname);
                Employee employee = EmployeeFactory.existingEmployee(person, employeeNumber);
                employee.getPerson().setGender(employeeGender);
                //employee.setEmployeeID(employeeID);
                return employee;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET firstname = ?, lastname = ?," +
                     " employeeGender = ?" +
                     " WHERE employeeNumber = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getPerson().getFirstName());
            ps.setString(2, employee.getPerson().getLastName());
            //ps.setString(3, employee.getEmployeeNumber());
            ps.setString(3, employee.getPerson().getGender().name());
            ps.setString(4, employee.getEmployeeNumber());

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Employee updated successfully");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating employee");
        }

    }

    @Override
    public void deleteEmployee(String employeeNumber) {
        String sql = "DELETE FROM employees WHERE employeeNumber = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employeeNumber);

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Employee deleted successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not delete employee");
        }

    }
}
