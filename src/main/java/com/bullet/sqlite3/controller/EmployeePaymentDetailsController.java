package com.bullet.sqlite3.controller;

import com.bullet.employee.strategy.PaymentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bullet.employee.strategy.PaymentType.*;

public class EmployeePaymentDetailsController implements Initializable {
    public TextField tf_employee_number;
    public ComboBox cb_payment_type;
    public ComboBox cb_pay_type_strategy;
    public TextField tf_department;
    public TextField tf_job_title;
    public TextField tf_hourly_rate;
    public TextField tf_hours_worked;
    public TextField tf_standard_hours;
    public TextField tf_daily_rate;
    public TextField tf_days_worked;
    public TextField tf_standard_days;
    public TextField tf_monthly_salary;
    public DatePicker dp_date_joined;
    public TableColumn col_employee_number;
    public TableColumn col_firstname;
    public TableColumn col_lastname;
    public TableColumn col_department;
    public TableColumn col_payment_type;

    /********    OBSERVABLE LISTS  ****/
    private final ObservableList paymentType = FXCollections.observableArrayList(HOURLY, DAILY, MONTHLY, CONTRACT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /******     COMBO BOXES   */
        cb_payment_type.setItems(paymentType);


        /******     TABLE VIEW   */

        /******     BUTTON EVENTS   */

        /******     TABLE COLUMNS   */

        /******     COMBO BOX   */


    }
}
