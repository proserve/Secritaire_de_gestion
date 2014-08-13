package com.andima.secritaire.client.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A sample with a control that creates a transparent stage that is centered on
 * your desktop. You can drag the stage with your mouse or use the scene
 * controls to minimize or close it. With a transparent stage, you must add
 * your own event handlers to perform these actions.
 */
public class AdvancedStageApp extends Application {

    //variables for storing initial position of the stage at the beginning of drag
    private double initX;
    private double initY;

    public Parent createContent() {




        // CREATE MIN AND CLOSE BUTTONS
        //create button for closing application
        Button close = new Button("Close me");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //in case we would like to close whole demo
                //javafx.application.Platform.exit();

                //however we want to close only this instance of stage
            }
        });

        //create button for minimalising application
        Button min = new Button("Minimize me");
        min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        // CREATE SIMPLE TEXT NODE
        Text text = new Text("JavaFX"); //20, 110,
        text.setFill(Color.WHITESMOKE);
        text.setEffect(new Lighting());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font(Font.getDefault().getFamily(), 50));

        // USE A LAYOUT VBOX FOR EASIER POSITIONING OF THE VISUAL NODES ON SCENE
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(60, 0, 0, 20));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(text, min, close);
        return vBox;
    }

    private Stage createDraggedStage(Group rootGroup) {
        //create stage which has set stage style transparent
        final Stage stage = new Stage(StageStyle.TRANSPARENT);

        //create root node of scene, i.e. group


        //create scene with set width, height and color
        Scene scene = new Scene(rootGroup, 400, 400, Color.TRANSPARENT);

        //set scene to stage
        stage.setScene(scene);

        //center stage on screen
        stage.centerOnScreen();

        //show the stage
        stage.show();
        rootGroup.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
            }
        });

        //when screen is dragged, translate it accordingly
        rootGroup.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
            }
        });
        //create dragger with desired size
        Rectangle dragger = new Rectangle(400, 600);

        rootGroup.getChildren().addAll(dragger, createContent());
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
                createDraggedStage(new Group()).show();
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}