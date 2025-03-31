package com.bullet.sqlite3.controller;

import com.bullet.person.Gender;
import com.bullet.sqlite3.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @FXML private TextField tf_lastname;
    ObservableList<Gender> gender = FXCollections.observableArrayList(MALE, FEMALE, UNKNOWN);
    ObservableList<Employee> employees = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cb_gender.setItems(FXCollections.observableList(gender));
        cb_gender.getItems().addAll(gender);
        //addListeners();
        btn_add_employee.setOnAction(event -> System.out.println("bullet"));
        System.out.println("Starting");

    }

//    private void addListeners() {
//        //btn_add_employee.setOnAction(event -> System.out.println("bullet"));
//
//    }
//
//    private void onAdd() {
//        System.out.println("Add button clicked");
//        tf_firstname.setText("Hie");
//    }
}
