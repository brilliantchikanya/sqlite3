package com.bullet.sqlite3.dao;

import com.bullet.employee.Employee;
import com.bullet.person.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{

    public EmployeeDAOImpl() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
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
        String sql = "INSERT INTO employees (firstname, lastname," +
                     "employeeGender) VALUES (?,?,?)";

        try (Connection conn = SQLITEDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql,
                                    PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, employee.getEmployeeFirstName());
            ps.setString(2, employee.getEmployeeLastName());
            //ps.setString(3, employee.getEmployeeNumber());
            ps.setString(3, employee.getEmployeeGender().name());
            ps.executeUpdate();
            // Get the generated ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                employee.setEmployeeID(rs.getInt(1));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM employees WHERE employeeID = ?";
        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long employeeID = (long) rs.getInt(1);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
//                String employeeNumber = rs.getString(4);
                String gender = rs.getString(4);
                Gender employeeGender = Gender.valueOf(gender);
                Employee employee = new Employee(firstname, lastname);
                employee.setEmployeeGender(employeeGender);
                employee.setEmployeeID(employeeID);
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
                     " WHERE employeeID = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getEmployeeFirstName());
            ps.setString(2, employee.getEmployeeLastName());
            //ps.setString(3, employee.getEmployeeNumber());
            ps.setString(3, employee.getEmployeeGender().name());
            ps.setInt(4, (int) employee.getEmployeeID());

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
    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE employeeID = ?";

        try (Connection conn = SQLITEDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);

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
