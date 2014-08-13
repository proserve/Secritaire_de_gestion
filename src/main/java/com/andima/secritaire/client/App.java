package com.andima.secritaire.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    private double initX;
    private double initY;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage appStage;
    public static boolean isMaxScreen = true;
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ProjectInterface.fxml"));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Secritaire de gestion");

        scene.getStylesheets().add("/css/win7glass.css");
        stage.setResizable(true);

        appStage = stage;
        appStage.show();
        toMaxScreen();
    }

    public static void toMaxScreen() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        appStage.setX(bounds.getMinX());
        appStage.setY(bounds.getMinY());
        appStage.setWidth(bounds.getWidth());
        appStage.setHeight(bounds.getHeight());
    }
}