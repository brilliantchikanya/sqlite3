module com.bullet.sqlite3 {
    requires javafx.controls;
    requires javafx.fxml;
    //requires com.bullet.employee;
    requires com.bullet.employee;
    requires com.bullet.person;
    requires java.sql;


    opens com.bullet.sqlite3 to javafx.fxml;
    exports com.bullet.sqlite3;
    exports com.bullet.sqlite3.controller;
    opens com.bullet.sqlite3.controller to javafx.fxml;
    exports com.bullet.sqlite3.model;
    opens com.bullet.sqlite3.model to javafx.fxml;
    exports com.bullet.sqlite3.dao;
    opens com.bullet.sqlite3.dao to javafx.fxml;
    exports com.bullet.sqlite3.view;
    opens com.bullet.sqlite3.view to javafx.fxml;
}