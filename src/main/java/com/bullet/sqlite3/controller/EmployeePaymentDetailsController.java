package com.bullet.sqlite3.controller;

import com.bullet.employee.strategy.*;
import com.bullet.person.MyDate;
import com.bullet.sqlite3.dao.EmployeePaymentDetailsDAOImpl;
import com.bullet.sqlite3.model.EmployeePaymentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.bullet.employee.strategy.PaymentType.*;

public class EmployeePaymentDetailsController implements Initializable {
    public TextField tf_employee_number;
    public ComboBox<PaymentType> cb_payment_type;
    public TextField tf_department;
    public TextField tf_job_title;
    public TextField tf_hourly_rate;
    public TextField tf_standard_hours;
    public TextField tf_daily_rate;
    public TextField tf_standard_days;
    public TextField tf_monthly_salary;
    public DatePicker dp_date_joined;   //need to add a default value in the UI
    public TableView<EmployeePaymentDetails> tbl_payment_details;
    public TableColumn<EmployeePaymentDetails, String> col_employee_number;
    //public TableColumn<Employee, String> col_firstname;
    //public TableColumn<Employee, String> col_lastname;
    public TableColumn<EmployeePaymentDetails, String> col_department;
    public TableColumn<EmployeePaymentDetails, String> col_payment_type;
    public Button btn_add;
    public Button btn_delete;
    public Button btn_clear;
    public Button btn_update;
    private EmployeePaymentDetails selectedItem;

    private final EmployeePaymentDetailsDAOImpl data = new EmployeePaymentDetailsDAOImpl();

    /********    OBSERVABLE LISTS  ****/
    private final ObservableList<PaymentType> paymentTypeList = FXCollections.observableArrayList(PaymentType.values());    /****
     the method PaymentType.values returns an array of Payment Types */

    //private final ObservableList payTypeStrategy = FXCollections.observableArrayList(HourlySalaryStrategy, DailySalaryStrategy, MonthlySalaryStrategy);
    private final ObservableList<EmployeePaymentDetails> paymentDetails = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /******     COMBO BOXES   */
        cb_payment_type.setItems(paymentTypeList);
        cb_payment_type.setValue(OTHER);    // SET A DEFAULT VALUE

        //cb_pay_type_strategy.setValue(new MonthlySalaryStrategy());


        /******      DATE PICKER      */
        // lets set the default value for our date picker to be today's date
        dp_date_joined.setValue(LocalDate.now());

        //TODO look into this binding
        //dp_date_joined.valueProperty().bindBidirectional(selectedItem.dateJoinedProperty());



        /******     TABLE VIEW   */
        tbl_payment_details.getSelectionModel().selectedItemProperty().addListener((o, old, new_) ->
        {
            selectedItem = new_;
            if (selectedItem != null) {
                //
                tf_employee_number.setText(selectedItem.getEmployeeNumber());
                tf_department.setText(selectedItem.getDepartment());

            }

        } );

        loadPaymentDetails();
        tbl_payment_details.setItems(paymentDetails);



        /******     BUTTON EVENTS   */
        addButtonListeners();

        /******     TABLE COLUMNS   */
        col_employee_number.setCellValueFactory(number ->
                number.getValue().employeeNumberProperty());
        col_department.setCellValueFactory(dpt ->
                dpt.getValue().departmentProperty());
        //col_firstname.setCellValueFactory(firstname ->
                //firstname.getValue().getPerson().getName().firstNameProperty());
        //col_lastname.setCellValueFactory(lastname ->
                //xlastname.getValue().getPerson().getName().lastNameProperty());
        col_payment_type.setCellValueFactory(paymenttype ->
                paymenttype.getValue().paymentTypeProperty().asString());



    }

    private void addButtonListeners() {
       btn_add.setOnAction(event -> onAdd());
       btn_update.setOnAction(event -> onUpdate());
       btn_delete.setOnAction(event -> onDelete());
       btn_clear.setOnAction(event -> onClear());
    }

    private void onClear() {
        System.out.println("Clear button clicked");
    }

    private void onDelete() {
        System.out.println("Delete button clicked");
    }

    private void onUpdate() {
        System.out.println("Update button clicked");
    }

    private void onAdd() {
        EmployeePaymentDetails details = new EmployeePaymentDetails();
        details.setEmployeeNumber(tf_employee_number.getText());
        details.setDepartment(tf_department.getText());
        details.setJobTitle(tf_job_title.getText());
        int year = dp_date_joined.getValue().getYear();
        int month = dp_date_joined.getValue().getMonthValue();
        int day = dp_date_joined.getValue().getDayOfMonth();
        details.setDateJoined(MyDate.create(year, month, day));
        details.setPaymentType(cb_payment_type.getValue());
        PaymentTypeStrategy pts = new MonthlySalaryStrategy();
        details.setPaymentTypeStrategy(pts);
        details.setHourlyRate(Float.parseFloat(tf_hourly_rate.getText()));
        //details.setHoursWorked(Float.parseFloat(tf_hours_worked.getText()));
        details.setStandardHours(Float.parseFloat(tf_standard_hours.getText()));
        details.setDailyRate(Float.parseFloat(tf_daily_rate.getText()));
        //details.setDaysWorked(Integer.parseInt(tf_days_worked.getText()));
        details.setStandardDays(Integer.parseInt(tf_standard_days.getText()));
        details.setMonthlySalary(Float.parseFloat(tf_monthly_salary.getText()));

        data.saveEmployeePaymentDetails(details);
        clearFields();
        loadPaymentDetails();
    }

    private void clearFields() {
        tf_employee_number.clear();
        tf_department.clear();
        tf_job_title.clear();
        dp_date_joined.setPromptText("Date Joined:");
        cb_payment_type.setPromptText("Payment Type:");
        //cb_pay_type_strategy.setPromptText("Pay Strategy:");
        tf_hourly_rate.clear();
        //tf_hours_worked.clear();
        tf_standard_hours.clear();
        tf_daily_rate.clear();
        //tf_days_worked.clear();
        tf_standard_days.clear();
        tf_monthly_salary.clear();

        tbl_payment_details.getSelectionModel().clearSelection();
    }

    public void loadPaymentDetails() {
        paymentDetails.clear();
        paymentDetails.addAll(data.getAllEmployeePaymentDetails());
    }


}
