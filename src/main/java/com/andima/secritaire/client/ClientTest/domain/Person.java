package com.andima.secritaire.client.ClientTest.domain;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private String firstName;
    private StringProperty lastName;
    private StringProperty email;

    public Person(String fName, String lName, String email) {
        this.firstName = fName;
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }

    public String firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }
}
