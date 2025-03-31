package com.bullet.sqlite3.model;

import com.bullet.person.Person;
import javafx.beans.property.*;

public class Employee {
    //private final LongProperty employeeID;
    private final ObjectProperty<Person> person;
    private final StringProperty employeeNumber;


    // create a private constructor since we will be using a factory class
    Employee(/*long employeeID, */Person person, String employeeNumber) {    // package private
        //this.employeeID = new SimpleLongProperty(this, "Employee ID", employeeID);    //TODO implement immutability
        this.person = new SimpleObjectProperty<>(this, "Person", person);
        this.employeeNumber = new SimpleStringProperty(this, "Employee Number", employeeNumber);
    }
    // access the employeeNumber property
    public StringProperty employeeNumberProperty() {
        return employeeNumber;
    }
    public String getEmployeeNumber() {
        return this.employeeNumber.get();
    }// no setter method since it will be an immutable

    // peron property access
    public ObjectProperty<Person> personProperty() {
        return this.person;
    }

    public Person getPerson() {
        return this.person.get();
    }

    public void setPerson(Person person) {
        this.person.set(person);
    }

//    public LongProperty employeeIDProperty() {
//        return this.employeeID;
//    }
//    public long getEmployeeID() {
//        return this.employeeID.get();
//    }






}
