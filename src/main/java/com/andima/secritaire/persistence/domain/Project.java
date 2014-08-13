package com.andima.secritaire.persistence.domain;

import com.andima.secritaire.core.event.project.ProjectDetail;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    public static final String START_DATE_NULL_VALIDATION_MESSAGE = "the start date of the project must not be Null";
    public static final String END_DATE_NULL_VALIDATION_MESSAGE = "the end date of the project must not be Null";
    public static final String NAME_BLANK_VALIDATION_MESSAGE = "the name of the project must not be Blank";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = NAME_BLANK_VALIDATION_MESSAGE)
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Project parentProject;

    @OneToMany(mappedBy = "parentProject")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Project> childrenProjects = new ArrayList<Project>();

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = START_DATE_NULL_VALIDATION_MESSAGE)
    @Column(name = "start_date", nullable = true)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = END_DATE_NULL_VALIDATION_MESSAGE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
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

    public List<Project> getChildrenProjects() {
        return childrenProjects;
    }

    public void setChildrenProjects(List<Project> childrenProjects) {
        this.childrenProjects = childrenProjects;
    }

    public ProjectDetail toProjectDetails() {
        ProjectDetail details = new ProjectDetail();
        BeanUtils.copyProperties(this, details);
        if (parentProject != null) {
            ProjectDetail parentDetail = new ProjectDetail();
            BeanUtils.copyProperties(parentProject, parentDetail);
            details.setParentProject(parentDetail);
            System.out.println("parentName" + parentProject.getName());
            System.out.println("parent  Name" + parentDetail.getName());
        }
        return details;
    }

    public static Project fromProjectDetails(ProjectDetail ProjectDetails) {
        Project project = new Project();
        BeanUtils.copyProperties(ProjectDetails, project);
        return project;
    }
}