package com.andima.secritaire.core.event.project;


import com.andima.secritaire.core.event.UpdatedEvent;

public class ProjectUpdatedEvent extends UpdatedEvent {
    private int key;
    private ProjectDetail projectDetails;

    public ProjectUpdatedEvent(int key, ProjectDetail projectDetails) {
        this.key = key;
        this.projectDetails = projectDetails;
    }

    public ProjectUpdatedEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public ProjectDetail getProjectDetails() {
        return projectDetails;
    }

    public static ProjectUpdatedEvent notFound(int key) {
        ProjectUpdatedEvent ev = new ProjectUpdatedEvent(key);
        ev.entityFound=false;
        return ev;
    }
}
