package com.bullet.sqlite3;

import com.bullet.sqlite3.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private Button btn_login;

   @FXML private void onLogin() {
       Stage stage = (Stage) btn_login.getScene().getWindow();
       stage.close();
       Model.getInstance().getViewFactory().showMainWindow();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(event -> onLogin());
    }
}