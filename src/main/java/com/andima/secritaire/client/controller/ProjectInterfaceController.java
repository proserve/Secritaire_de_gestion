package com.andima.secritaire.client.controller;

import com.andima.secritaire.client.domain.Project;
import com.andima.secritaire.client.service.ProjectService;
import com.andima.secritaire.client.util.SpringUtil;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.andima.secritaire.client.App.*;

public class ProjectInterfaceController {
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
    @FXML
    private AnchorPane tableAnchorPane;

    ProjectService projectService = SpringUtil.getBean(ProjectService.class);

    private double initX;
    private double initY;
    private TableControl<Project> projectTableControl = new TableControl<Project>(Project.class);
    private PopOver popOver;

/*
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
        *//*DateColumn<Project> dateColumn = new DateColumn("startDate");
        dateColumn.setText("Start Date");
        dateColumn.setPrefWidth(200);
        startDateColumn = dateColumn;
        projectTable.getColumns().add(dateColumn);*//*
    }*/

    @FXML
    void initialize() {
        assert tableAnchorPane != null : "fx:id=\"tableAnchorPane\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert endDateColumn != null : "fx:id=\"endDateColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert startDateColumn != null : "fx:id=\"startDateColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert projectTable != null : "fx:id=\"projectTable\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert parentColumn != null : "fx:id=\"parentColumn\" was not injected: check your FXML file 'ProjectInterface.fxml'.";
        assert tableAnchorPane != null;
        AnchorPane.setBottomAnchor(projectTableControl, 0.0);
        AnchorPane.setTopAnchor(projectTableControl, 0.0);
        AnchorPane.setLeftAnchor(projectTableControl, 0.0);
        AnchorPane.setRightAnchor(projectTableControl, 0.0);
        tableAnchorPane.getChildren().add(createProjectTableControl());
    }

    private TableControl<Project> createProjectTableControl() {
        TextColumn textColumn = new TextColumn("name");
        textColumn.setRequired(true);

        projectTableControl.addColumn(textColumn);
        DateColumn startDateColumn = new DateColumn("startDate");
        startDateColumn.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        startDateColumn.setRequired(true);
        projectTableControl.addColumn(startDateColumn);
        DateColumn endDateColumn = new DateColumn("endDate");
        endDateColumn.setRequired(true);
        endDateColumn.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        projectTableControl.addColumn(endDateColumn);
        projectTableControl.setAgileEditing(false);
        projectTableControl.setReloadOnCriteriaChange(true);
        TypeAheadColumn<Project, Project> parentColumn = new TypeAheadColumn("parentProject");
        //createSampleProjects();
        final List<Project> allProject = projectService.getAllProject();

        for (Project project : allProject) {
            parentColumn.addItem(project.getName(), project);
        }
        parentColumn.setRequired(true);
        projectTableControl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {
                @Override
                public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                    showProjectDetails(newValue);
                }
            });
        projectTableControl.addColumn(parentColumn);;
        projectTableControl.setController(new TableController<Project>() {
                                              @Override
                                              public TableData<Project> loadData(int i, List<TableCriteria> tableCriterias,
                                                                                 List<String> strings, List<TableColumn.SortType> sortTypes, int i2) {
                                                  return new TableData<Project>(allProject, true, 500);
                                              }

                                              public List<Project> insert(List<Project> newRecords) {
                                                  List<Project> createdList = new ArrayList<Project>();
                                                  for (Project newRecord : newRecords) {
                                                      createdList.add(projectService.create(newRecord));
                                                  }
                                                  return newRecords;
                                              }

                                              public java.util.List<Project> update(List<Project> records) {
                                                  for (Project record : records) {
                                                      projectService.create(record);
                                                  }
                                                  return records;
                                              }

                                              public void delete(List<Project> records) {
                                                  for (Project record : records) {
                                                      projectService.delete(record.getId());
                                                  }
                                              }
                                          });

        setTableColumnSize(projectTableControl, tableAnchorPane.getPrefWidth());
        projectTableControl.reload();
        projectTableControl.setMaxRecord(20);
        projectTableControl.setAgileEditing(true);
        return projectTableControl;
    }

    private void createSampleProjects() {
        for (int i = 0; i < 100; i++) {
            projectService.create(new Project(new Date(), new Date(), "project "+ i));
        }
    }

    private void  setTableColumnSize(TableControl projectTableControl, double width) {
        List<TableColumn> columns = projectTableControl.getColumns();
        for (TableColumn column : columns) {
            column.setPrefWidth(width/columns.size());
        }
    }


    public void handleNewProject(ActionEvent actionEvent) {

    }

    public void handleEditProject(ActionEvent actionEvent) {
        projectTableControl.edit();
    }

    public void handleDeleteProject(ActionEvent actionEvent) {
        projectTableControl.delete();
    }

    public void mouseDragged(MouseEvent me) {
        appStage.setX(me.getScreenX() - initX);
        appStage.setY(me.getScreenY() - initY);
    }

    public void mouseReleased(MouseEvent me) {
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
            // Project is null, remove all the text.
            nameLabel.setText("");
            endDateLabel.setText("");
            startDateLabel.setText("");
            IDLabel.setText("");
            parentNameLabel.setText("");
        }
    }

    public void maximizeApp(MouseEvent me) {
        if (isMaxScreen) {
            appStage.setWidth(1024);
            appStage.setHeight(800);
            appStage.centerOnScreen();
            isMaxScreen = false;
        } else {
            toMaxScreen();
            isMaxScreen = true;
        }

    }

    public void minimizeApp(MouseEvent me) {
        appStage.setIconified(true);
    }

    public void closeApp(MouseEvent me) {
        appStage.close();
    }

}
