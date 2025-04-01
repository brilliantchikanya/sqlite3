package com.bullet.sqlite3.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Name {
    private final StringProperty firstName;
    private final StringProperty lastName;

    public Name() {
        this.firstName = new SimpleStringProperty(this, "First Name", "");
        this.lastName = new SimpleStringProperty(this, "Last Name", "");
    }

    public Name(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(this, "First Name", firstName);
        this.lastName = new SimpleStringProperty(this, "Last Name", lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
}
