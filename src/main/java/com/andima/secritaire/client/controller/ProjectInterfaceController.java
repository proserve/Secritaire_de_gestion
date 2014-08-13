package com.andima.secritaire.client.controller;

import com.andima.secritaire.client.domain.Project;
import com.andima.secritaire.client.service.ProjectService;
import com.andima.secritaire.client.util.SpringUtil;
import com.panemu.tiwulfx.table.DateColumn;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.andima.secritaire.client.App.appStage;
import static com.andima.secritaire.client.App.isMaxScreen;
import static com.andima.secritaire.client.App.toMaxScreen;

public class ProjectInterfaceController {
    @FXML
    private AnchorPane tableAnchorPane;

    @FXML
    private TableColumn<Project, Date> endDateColumn;

    @FXML
    private TableColumn nameColumn = new TableColumn();

    @FXML
    private TableColumn<Project, Date> startDateColumn = new DateColumn<Project>();

    @FXML
    private TableColumn parentColumn;

    @FXML
    private AnchorPane view;

    @FXML
    private TableView<Project> projectTable;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label parentNameLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private Label endDateLabel;
    @FXML
    private Label nameLabel;

    ProjectService projectService = SpringUtil.getBean(ProjectService.class);

    private double initX;
    private double initY;

    public AnchorPane getView() {
        return view;
    }

    public void print() {

    }

    public void initialsTable() {
        List<Project> col = projectService.getAllProject();
        final ObservableList<Project> data = FXCollections.observableArrayList(col);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Project, Date>("endDate"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Project, Date>("startDate"));
        parentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> p) {
                if (p.getValue().getParentProject() != null) {
                    return p.getValue().getParentProject().nameProperty();
                }
                return new SimpleObjectProperty<String>("/");
            }
        });
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        List<String> parents = new ArrayList<String>();
        for (Project project : col) {
            parents.add(project.getName());
        }
        ObservableList<String> parentData = FXCollections.observableArrayList(parents);
        parentColumn.setCellFactory(ComboBoxTableCell.forTableColumn(parentData));
        projectTable.setItems(data);
        projectTable.setEditable(true);
        projectTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {
            @Override
            public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                showProjectDetails(newValue);
            }
        });
        /*DateColumn<Project> dateColumn = new DateColumn("startDate");
        dateColumn.setText("Start Date");
        dateColumn.setPrefWidth(200);
        startDateColumn = dateColumn;
        projectTable.getColumns().add(dateColumn);*/
    }

    @FXML
    void initialize() {
        assert tableAnchorPane != null : "fx:id=\"tableAnchorPane\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert endDateColumn != null : "fx:id=\"endDateColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert startDateColumn != null : "fx:id=\"startDateColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert projectTable != null : "fx:id=\"projectTable\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert parentColumn != null : "fx:id=\"parentColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        initialsTable();
    }

    public void handleNewProject(ActionEvent actionEvent) {

    }

    public void handleEditProject(ActionEvent actionEvent) {

    }

    public void handleDeleteProject(ActionEvent actionEvent) {

    }

    public void mouseDragged(MouseEvent me){
        appStage.setX(me.getScreenX() - initX);
        appStage.setY(me.getScreenY() - initY);
    }
    public void mouseReleased(MouseEvent me){
        initX = me.getScreenX() - appStage.getX();
        initY = me.getScreenY() - appStage.getY();
    }

    private void showProjectDetails(Project project) {
        if (project != null) {
            // Fill the labels with info from the project object.
            nameLabel.setText(project.getName());
            endDateLabel.setText(project.getEndDate().toLocaleString());
            startDateLabel.setText(project.getStartDate().toLocaleString());
            IDLabel.setText(Integer.toString(project.getId()));
            if (project.getParentProject() != null)
                parentNameLabel.setText(project.getParentProject().getName());
            else parentNameLabel.setText("");

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            nameLabel.setText("");
            endDateLabel.setText("");
            startDateLabel.setText("");
            IDLabel.setText("");
            parentNameLabel.setText("");
        }
    }
    public void maximizeApp(MouseEvent me){
        if(isMaxScreen){
            appStage.setWidth(1024);
            appStage.setHeight(800);
            appStage.centerOnScreen();
            isMaxScreen = false;
        }else{
            toMaxScreen();
            isMaxScreen = true;
        }

    }
    public void minimizeApp(MouseEvent me){
        appStage.setIconified(true);
    }
    public void closeApp(MouseEvent me){
        appStage.close();
    }

}
