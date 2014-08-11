package com.andima.secritaire.client.ClientTest;

import com.andima.secritaire.client.ClientTest.controller.EnterDetailsController;
import com.andima.secritaire.client.ClientTest.controller.WelcomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppFactory.class);

        HBox box = new HBox(10);
        EnterDetailsController enterDetailsController = context.getBean(EnterDetailsController.class);
        box.getChildren().add(enterDetailsController.getView());
        WelcomeController welcomeController = context.getBean(WelcomeController.class);
        box.getChildren().add(welcomeController.getView());

        Scene scene = new Scene(box, 600, 200);
        System.out.println("style sheet before is ==> " + scene.getStylesheets().size());
        scene.getStylesheets().add("/fxmlapp.css");
        System.out.println("style sheet after is ==> " + scene.getStylesheets().size());
        stage.setScene(scene);
        stage.setTitle("JFX2.0 Sprung");
        stage.show();
    }
}