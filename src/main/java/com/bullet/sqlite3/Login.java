package com.bullet.sqlite3;

import com.bullet.sqlite3.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}