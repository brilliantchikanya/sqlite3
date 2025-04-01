package com.bullet.sqlite3.controller;

import com.bullet.person.Gender;
import com.bullet.sqlite3.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bullet.person.Gender.*;

public class EmployeeController implements Initializable {
    public ComboBox<Gender> cb_gender;
    public TextField tf_employee_number;
    public Button btn_add_employee;
    public Button btn_update_employee;
    public Button btn_delete_employee;
    public Button btn_cancel;
    public TableView<Employee> tbl_employees;
    public TextField tf_firstname;
    public TextField tf_lastname;
    public TextField tf_employeeNumber;
    ObservableList<Gender> gender = FXCollections.observableArrayList(MALE, FEMALE, UNKNOWN);
    ObservableList<Employee> employees = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_gender.setItems(gender);
        addListeners();
    }

    private void addListeners() {
        btn_add_employee.setOnAction(event -> onAdd());
    }

    private void onAdd() {
        System.out.println("Add");
    }
}
