package com.andima.secritaire.core.event.project;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

/**
 * Created by proserve on 07/08/2014.
 */
public class ProjectDetail {
    @NotBlank
    private String name;
    private ProjectDetail parentProject;
    private List<ProjectDetail> childrenProjects;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectDetail getParentProject() {
        return parentProject;
    }

    public void setParentProject(ProjectDetail parentProject) {
        this.parentProject = parentProject;
    }

    public List<ProjectDetail> getChildrenProjects() {
        return childrenProjects;
    }

    public void setChildrenProjects(List<ProjectDetail> childrenProjects) {
        this.childrenProjects = childrenProjects;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
