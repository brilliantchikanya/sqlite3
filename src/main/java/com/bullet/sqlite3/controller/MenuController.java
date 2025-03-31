package com.bullet.sqlite3.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button btn_employee;
    public Button btn_payment_details;
    public Button btn_logout;
    public Button btn_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_employee.setOnAction(event -> System.out.println("Employee clicked"));

    }
}
