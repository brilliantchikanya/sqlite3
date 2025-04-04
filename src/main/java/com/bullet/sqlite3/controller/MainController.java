package com.bullet.sqlite3.controller;

import com.bullet.sqlite3.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().selectedMenuOptionProperty()
                .addListener((o, old, new_) -> {
                    switch (new_) {
                        //case EMPLOYEE -> main.setCenter(Model.getInstance().getViewFactory().getEmployeeView());
                        case PAYMENT_DETAILS -> main.setCenter(Model.getInstance().getViewFactory().getEmployeePaymentDetailsView());
                        case EXIT -> {
                            Stage stage = (Stage) main.getScene().getWindow();
                            Model.getInstance().getViewFactory().closeStage(stage);
                        }
                        case LOGOUT -> {
                            Stage stage = (Stage) main.getScene().getWindow();
                            Model.getInstance().getViewFactory().closeStage(stage);
                            Model.getInstance().getViewFactory().showLoginWindow();
                        }
                        default -> main.setCenter(Model.getInstance().getViewFactory().getEmployeeView());
                    }
                } );
    }
}
