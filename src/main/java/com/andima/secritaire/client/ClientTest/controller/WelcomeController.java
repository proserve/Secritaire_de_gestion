package com.andima.secritaire.client.ClientTest.controller;

import com.andima.secritaire.client.ClientTest.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class WelcomeController
{
    @FXML private Node view;
    @FXML private Label welcomeLabel;

    @Autowired
    private Person person;

    public WelcomeController() {
    }

    public Node getView()
    {
        return view;
    }

    public void showWelcome(ActionEvent event)
    {
       welcomeLabel.setText(String.format("Welcome %s %s", person.getFirstName(), person.getLastName()));
    }
}