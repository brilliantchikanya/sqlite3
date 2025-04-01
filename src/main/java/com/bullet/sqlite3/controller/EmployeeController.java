package com.bullet.sqlite3.controller;

import com.bullet.person.Gender;
import com.bullet.person.Person;
import com.bullet.sqlite3.dao.EmployeeDAOImpl;
import com.bullet.sqlite3.model.Employee;
import com.bullet.sqlite3.model.EmployeeFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bullet.person.Gender.*;

public class EmployeeController implements Initializable {
    private final EmployeeDAOImpl data = new EmployeeDAOImpl();

    public ComboBox<Gender> cb_gender;
    public Button btn_add_employee;
    public Button btn_update_employee;
    public Button btn_delete_employee;
    public Button btn_cancel;
    public TableView<Employee> tbl_employees;
    public TextField tf_firstname;
    public TextField tf_lastname;
    public TextField tf_employeeNumber;
    public TableColumn<Employee, String> col_emp_number;
    public TableColumn<Employee, String> col_firstname;
    public TableColumn<Employee, String> col_lastname;
    public TableColumn<Employee, String> col_gender;
    ObservableList<Gender> gender = FXCollections.observableArrayList(MALE, FEMALE, UNKNOWN);
    ObservableList<Employee> employees = FXCollections.observableArrayList(data.getAllEmployees());


    public EmployeeController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //col_emp_number.setCellValueFactory(employeeNumber -> employeeNumber.getValue().employeeNumberProperty());
        //col_firstname.setCellValueFactory(col_firstname -> col_firstname.getValue().personProperty().asString());

        cb_gender.setItems(gender);
        tbl_employees.setItems(employees);
        addListeners();
    }

    private void addListeners() {
        btn_add_employee.setOnAction(event -> onAdd());
    }

    private void onAdd() {
        System.out.println("Add");
        String employeeNumber = tf_employeeNumber.getText();
        String firstname = tf_firstname.getText();
        String lastname = tf_lastname.getText();
        Gender gender = cb_gender.getValue();
        Employee employee = EmployeeFactory.newEmployee(new Person(firstname, lastname));
        employee.getPerson().setGender(gender);
        data.saveEmployee(employee);
    }
}
