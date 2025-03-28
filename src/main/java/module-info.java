module com.bullet.sqlite3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.bullet.employee;
    requires com.bullet.person;


    opens com.bullet.sqlite3 to javafx.fxml;
    exports com.bullet.sqlite3;
}