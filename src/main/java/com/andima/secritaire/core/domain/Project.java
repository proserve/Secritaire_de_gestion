package com.andima.secritaire.core.domain;

import java.util.Date;
import java.util.List;

public class Project {
    private String name;
    private Project parentProject;
    private List<Project> childrenProjects;
    private Date startDate;
    private Date endDate;
}