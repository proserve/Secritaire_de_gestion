package com.andima.secritaire.core.event.project;

import java.util.Collections;
import java.util.Map;

/**
 * Created by proserve on 07/08/2014.
 */
public class AllProjectsEvent {
    private final Map<Integer, ProjectDetail> projects;

    public AllProjectsEvent(Map<Integer, ProjectDetail> projects) {
        this.projects =  Collections.unmodifiableMap(projects);;
    }

    public Map<Integer, ProjectDetail> getProjects() {
        return projects;
    }
}
