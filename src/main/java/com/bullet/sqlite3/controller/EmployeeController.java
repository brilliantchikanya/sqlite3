package com.bullet.sqlite3.controller;

import com.bullet.person.Gender;
//import com.bullet.person.Person;
import com.bullet.sqlite3.dao.EmployeeDAOImpl;
import com.bullet.sqlite3.dao.EmployeePaymentDetailsDAOImpl;
import com.bullet.sqlite3.model.Employee;
import com.bullet.sqlite3.model.EmployeeFactory;
import com.bullet.sqlite3.model.EmployeePaymentDetails;
import com.bullet.sqlite3.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bullet.person.Gender.*;

public class EmployeeController implements Initializable {
    private final EmployeeDAOImpl data = new EmployeeDAOImpl();

    public ComboBox<Gender> cb_gender;
    public Button btn_add_employee;
    public Button btn_update_employee;
    public Button btn_delete_employee;
    public Button btn_cancel;
    public TableView<Employee> tbl_employees;
    public TextField tf_firstname;
    public TextField tf_lastname;
    public TextField tf_employeeNumber;
    public TableColumn<Employee, String> col_emp_number;
    public TableColumn<Employee, String> col_firstname;
    public TableColumn<Employee, String> col_lastname;
    public TableColumn<Employee, String> col_gender;
    ObservableList<Gender> gender = FXCollections.observableArrayList(MALE, FEMALE, UNKNOWN);
    ObservableList<Employee> employees = FXCollections.observableArrayList();
    private Employee selectedTableItem;


    public EmployeeController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set the table column values
        col_emp_number.setCellValueFactory(employeeNumber ->
                employeeNumber.getValue().employeeNumberProperty());
        col_firstname.setCellValueFactory(col_firstname ->
                col_firstname.getValue().getPerson().getName().firstNameProperty());
        col_lastname.setCellValueFactory(col_lastname ->
                col_lastname.getValue().getPerson().getName().lastNameProperty());
        col_gender.setCellValueFactory(col_gender ->
                col_gender.getValue().getPerson().genderProperty().asString());

        // set values for the combo box
        cb_gender.setItems(gender);
        cb_gender.setPromptText("Gender");
        // set values for the table
        tbl_employees.setItems(employees);
        loadEmployees();

        // add button listeners
        addListeners();

        // add selected Employee object listener
        tbl_employees.getSelectionModel().selectedItemProperty()
                .addListener((o, old, new_) ->
                {
                    selectedTableItem = new_;
                    if (selectedTableItem != null) {
                        tf_employeeNumber.setText(selectedTableItem.getEmployeeNumber());
                        tf_firstname.setText(selectedTableItem.getPerson().getName().getFirstName());
                        tf_lastname.setText(selectedTableItem.getPerson().getName().getLastName());
                        cb_gender.setValue(selectedTableItem.getPerson().getGender());

                    }
                });

    }

    private void loadEmployees() {
        // first clear the list
        employees.clear();
        // then reload the list from the database
        employees.addAll(data.getAllEmployees());


    }
    private void clearFields() {
        tf_employeeNumber.clear();
        tf_firstname.clear();
        tf_lastname.clear();
        selectedTableItem = null;
        cb_gender.setValue(UNKNOWN);
        tbl_employees.getSelectionModel().clearSelection();
    }

    private void addListeners() {
        btn_add_employee.setOnAction(event -> onAdd());
        btn_update_employee.setOnAction(event -> onUpdate());
        btn_delete_employee.setOnAction(event -> onDelete());
        btn_cancel.setOnAction(event -> onClear());
    }

    private void onDelete() {
        //TODO logic here
        if (selectedTableItem != null) {
            data.deleteEmployee(selectedTableItem.getEmployeeNumber());
            clearFields();
            loadEmployees();
        }


    }

    private void onUpdate() {
        if (selectedTableItem != null) {
        String employeeNumber = tf_employeeNumber.getText();
        String firstname = tf_firstname.getText();
        String lastname = tf_lastname.getText();
        Gender gender = cb_gender.getValue();
        Person person = new Person(firstname, lastname);
        person.setGender(gender);
        Employee employee5 = EmployeeFactory.existingEmployee(person, employeeNumber);
        data.updateEmployee(employee5);
        clearFields();
        loadEmployees();
        }
    }

    private void onClear() {
        clearFields();
    }

    private void onAdd() {
        String employeeNumber = tf_employeeNumber.getText();
        String firstname = tf_firstname.getText();
        String lastname = tf_lastname.getText();
        Gender gender = cb_gender.getValue();
        Employee employee = EmployeeFactory.newEmployee(new Person(firstname, lastname));
        employee.getPerson().setGender(gender);
        data.saveEmployee(employee);
        //EmployeePaymentDetailsController controller = new EmployeePaymentDetailsController();
        EmployeePaymentDetailsDAOImpl pd_data = new EmployeePaymentDetailsDAOImpl();
        EmployeePaymentDetails pd = new EmployeePaymentDetails();
        pd.setEmployeeNumber(employee.getEmployeeNumber());
        pd_data.saveEmployeePaymentDetails(pd);
        //EmployeePaymentDetailsController cont = new EmployeePaymentDetailsController();
        //cont.loadPaymentDetails();
        clearFields();
        loadEmployees();
    }
}
