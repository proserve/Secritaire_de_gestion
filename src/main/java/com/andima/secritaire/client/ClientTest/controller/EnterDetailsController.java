package com.andima.secritaire.client.ClientTest.controller;

/**
 * Created by proserve on 11/08/2014.
 */

import com.andima.secritaire.client.ClientTest.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class EnterDetailsController
{
    @FXML private Node view;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    @Autowired
    private Person person;

    public Node getView()
    {
        return view;
    }

    public Person getPerson()
    {
        return person;
    }

    public void save(ActionEvent event)
    {
        person.setFirstName(firstNameField.getText());
        person.setLastName(lastNameField.getText());
    }
}
