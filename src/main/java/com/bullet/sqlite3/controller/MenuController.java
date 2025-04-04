package com.bullet.sqlite3.controller;

import com.bullet.sqlite3.model.Model;
import com.bullet.sqlite3.view.MenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bullet.sqlite3.view.MenuOption.EMPLOYEE;
import static com.bullet.sqlite3.view.MenuOption.PAYMENT_DETAILS;

public class MenuController implements Initializable {
    public Button btn_employee;
    public Button btn_payment_details;
    public Button btn_logout;
    public Button btn_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btn_employee.setOnAction(event -> Model.getInstance().getViewFactory().setSelectedMenuOption(EMPLOYEE));
        addListeners();

    }

    private void addListeners() {
        btn_payment_details.setOnAction(event ->
                Model.getInstance().getViewFactory().setSelectedMenuOption(PAYMENT_DETAILS));
        btn_employee.setOnAction(event ->
                Model.getInstance().getViewFactory().setSelectedMenuOption(EMPLOYEE));
        btn_logout.setOnAction(event ->
                Model.getInstance().getViewFactory().setSelectedMenuOption(MenuOption.LOGOUT));
        btn_exit.setOnAction(event ->
        {
            Stage stage = (Stage) btn_logout.getScene().getWindow();
            stage.close();
        });
    }
}
