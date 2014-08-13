package com.andima.secritaire.client.ClientTest.domain;

import com.andima.secritaire.core.event.project.ProjectDetail;
import javafx.beans.property.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
public class Project {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Project> parentProject = new SimpleObjectProperty<Project>();
    private ObjectProperty<Date> startDate = new SimpleObjectProperty<Date>();
    private ObjectProperty<Date> endDate = new SimpleObjectProperty<Date>();

    public Project(Date endDate, Date startDate, String name) {
        this.endDate = new SimpleObjectProperty<Date>(endDate);
        this.startDate = new SimpleObjectProperty<Date>(startDate);
        this.name = new SimpleStringProperty(name);
    }

    public Project() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Project getParentProject() {
        return parentProject.get();
    }

    public ObjectProperty<Project> parentProjectProperty() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject.set(parentProject);
    }

    public Date getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<Date> startDateProperty() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate.set(startDate);
    }

    public Date getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<Date> endDateProperty() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate.set(endDate);
    }

    public ProjectDetail toProjectDetails() {
        ProjectDetail details = new ProjectDetail();
        details.setName(getName());
        details.setStartDate(getStartDate());
        details.setEndDate(getEndDate());
        if(getParentProject() != null){
            ProjectDetail parentDetails = new ProjectDetail();
            BeanUtils.copyProperties(parentDetails, getParentProject());
            parentDetails.setParentProject(parentDetails);
        }
        return details;
    }

    public static Project fromProjectDetails(ProjectDetail details) {
        Project project = new Project();
        BeanUtils.copyProperties(details, project);
        ProjectDetail parentDetail = details.getParentProject();
        if (parentDetail != null) {
            Project parent = new Project();
            BeanUtils.copyProperties(parentDetail, parent);
            project.setParentProject(parent);

        }

        return project;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
