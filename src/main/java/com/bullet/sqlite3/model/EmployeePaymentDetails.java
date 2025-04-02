package com.bullet.sqlite3.model;

import com.bullet.employee.strategy.PaymentDetailsInterface;
import com.bullet.employee.strategy.PaymentType;
import com.bullet.employee.strategy.PaymentTypeStrategy;
import com.bullet.person.MyDate;
import javafx.beans.property.*;

public class EmployeePaymentDetails implements PaymentDetailsInterface {
    //private final ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
    private final StringProperty employeeNumber = new SimpleStringProperty(this, "Employee Number", "");
    private final StringProperty department = new SimpleStringProperty(this, "Department", "");
    private final StringProperty jobTitle = new SimpleStringProperty(this, "Job Title", "");
    private final ObjectProperty<MyDate> dateJoined = new SimpleObjectProperty<>(this, "Date Joined", null);
    private final ObjectProperty<PaymentType> paymentType = new SimpleObjectProperty<>(this, "Payment Type", null);
    private final ObjectProperty<PaymentTypeStrategy> paymentTypeStrategy = new SimpleObjectProperty<>(this, "Payment Type Strategy", null);

    private final FloatProperty hourlyRate = new SimpleFloatProperty(this, "Hourly rate", 0);
    private final FloatProperty hoursWorked = new SimpleFloatProperty(this, "Hours worked", 0);
    private final FloatProperty standardHours = new SimpleFloatProperty(this, "Standard hours", 0);
    private final FloatProperty dailyRate = new SimpleFloatProperty(this, "Daily rate", 0);
    private final IntegerProperty daysWorked = new SimpleIntegerProperty(this, "Days worked", 0);
    private final IntegerProperty standardDays = new SimpleIntegerProperty(this, "Standard days", 0);
    private final FloatProperty monthlySalary = new SimpleFloatProperty(this, "Monthly salary", 0);


    public EmployeePaymentDetails() {
        //this.employee.set(employee);
    }

    public String getEmployeeNumber() {
        return this.employeeNumber.get();
    }

    public StringProperty employeeNumberProperty() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }

    public MyDate getDateJoined() {
        return dateJoined.get();
    }

    public ObjectProperty<MyDate> dateJoinedProperty() {
        return dateJoined;
    }

    public void setDateJoined(MyDate dateJoined) {
        this.dateJoined.set(dateJoined);
    }

    public PaymentType getPaymentType() {
        return paymentType.get();
    }

    public ObjectProperty<PaymentType> paymentTypeProperty() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType.set(paymentType);
    }

    public PaymentTypeStrategy getPaymentTypeStrategy() {
        return paymentTypeStrategy.get();
    }

    public ObjectProperty<PaymentTypeStrategy> paymentTypeStrategyProperty() {
        return paymentTypeStrategy;
    }

    public void setPaymentTypeStrategy(PaymentTypeStrategy paymentTypeStrategy) {
        this.paymentTypeStrategy.set(paymentTypeStrategy);
    }

    public float getHourlyRate() {
        return hourlyRate.get();
    }

    public FloatProperty hourlyRateProperty() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate.set(hourlyRate);
    }

    public float getHoursWorked() {
        return hoursWorked.get();
    }

    public FloatProperty hoursWorkedProperty() {
        return hoursWorked;
    }

    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked.set(hoursWorked);
    }

    public float getStandardHours() {
        return standardHours.get();
    }

    public FloatProperty standardHoursProperty() {
        return standardHours;
    }

    public void setStandardHours(float standardHours) {
        this.standardHours.set(standardHours);
    }

    public float getDailyRate() {
        return dailyRate.get();
    }

    public FloatProperty dailyRateProperty() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate.set(dailyRate);
    }

    public int getDaysWorked() {
        return daysWorked.get();
    }

    public IntegerProperty daysWorkedProperty() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked.set(daysWorked);
    }

    public int getStandardDays() {
        return standardDays.get();
    }

    public IntegerProperty standardDaysProperty() {
        return standardDays;
    }

    public void setStandardDays(int standardDays) {
        this.standardDays.set(standardDays);
    }

    public float getMonthlySalary() {
        return monthlySalary.get();
    }

    public FloatProperty monthlySalaryProperty() {
        return monthlySalary;
    }

    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary.set(monthlySalary);
    }

    @Override
    public double basicEarnings() {
        //TODO add logic for calculating basic earnings
        return 0;
    }

    public static void main(String[] args) {
        Employee employee1 = EmployeeFactory.newEmployee(new Person("Brilliant", "Chikanya"));
        System.out.println(employee1);
    }
}
