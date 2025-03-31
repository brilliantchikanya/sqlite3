module com.bullet.sqlite3 {
    requires javafx.controls;
    requires javafx.fxml;
    //requires com.bullet.employee;
    requires com.bullet.employee;
    requires com.bullet.person;
    requires java.sql;


    opens com.bullet.sqlite3 to javafx.fxml;
    exports com.bullet.sqlite3;
}