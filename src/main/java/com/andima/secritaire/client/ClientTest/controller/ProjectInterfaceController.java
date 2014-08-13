package com.andima.secritaire.client.ClientTest.controller;

import com.andima.secritaire.client.ClientTest.domain.Project;
import com.andima.secritaire.client.ClientTest.service.ProjectService;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    ProjectService projectService;

    public AnchorPane getView() {
        return view;
    }

    public void print() {
        createContent();
    }

    public void createContent() {
        StringConverter<Object> sc = new StringConverter<Object>() {
            public String toString(Object t) {
                return t == null ? null : t.toString();
            }

            public Object fromString(String string) {
                return string;
            }
        };
        List<Project> col = projectService.requestAllProject();
        for (Project project : col) {
            System.out.println("loop start here");
            Project parentProject = project.getParentProject();
            if (parentProject != null) {
                System.out.println("the id is " + parentProject.getName());
            }
        }
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
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn(sc));

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
        DateColumn<Project> dateColumn = new DateColumn("startDate");
        dateColumn.setText("Start Date");
        dateColumn.setPrefWidth(200);
        startDateColumn = dateColumn;
        projectTable.getColumns().add(dateColumn);
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

    }

    public void handleNewProject(ActionEvent actionEvent) {

    }

    public void handleEditProject(ActionEvent actionEvent) {

    }

    public void handleDeleteProject(ActionEvent actionEvent) {

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

}
