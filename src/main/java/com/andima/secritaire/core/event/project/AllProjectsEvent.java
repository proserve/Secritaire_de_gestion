package com.andima.secritaire.core.event.project;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by proserve on 07/08/2014.
 */
public class AllProjectsEvent {
    private final List<ProjectDetail> projects;

    public AllProjectsEvent(List<ProjectDetail> projects) {
        this.projects =  Collections.unmodifiableList(projects);;
    }

    public Collection<ProjectDetail> getProjects() {
        return projects;
    }
}
