package com.andima.secritaire.client.ClientTest;

import com.andima.secritaire.client.ClientTest.config.AppFactory;
import com.andima.secritaire.client.ClientTest.controller.ProjectInterfaceController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppFactory.class);
        ProjectInterfaceController interfaceController = context.getBean(ProjectInterfaceController.class);
        Scene scene = new Scene(interfaceController.getView());
        stage.setScene(scene);
        stage.setTitle("Secritaire de gestion");
        stage.show();
    }
}