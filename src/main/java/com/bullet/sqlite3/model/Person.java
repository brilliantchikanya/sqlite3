package com.bullet.sqlite3.model;

import com.bullet.person.Gender;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Person {
    private final ObjectProperty<Name> name;
    private final ObjectProperty<Gender> gender;

    public Person() {
        this.name = new SimpleObjectProperty<>();
        this.gender = new SimpleObjectProperty<>();
    }
    public Person(String firstname, String lastname) {
        Name name= new Name(firstname, lastname);
        this.name = new SimpleObjectProperty<>(name);
        this.gender = new SimpleObjectProperty<>();
    }
    public Person(Name name) {
        this.name = new SimpleObjectProperty<>(this, "Name", name);
        this.gender = new SimpleObjectProperty<>();
    }

    public Name getName() {
        return name.get();
    }

    public ObjectProperty<Name> nameProperty() {
        return name;
    }

    public void setName(Name name) {
        this.name.set(name);
    }

    public Gender getGender() {
        return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public static void main(String[] args) {
        String fn = "paul";
        String ln = "chiks";
        Person person = new Person(fn, ln);
        System.out.println(person.getName());
    }
}
