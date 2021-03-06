package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.ReadEvent;

/**
 * Created by proserve on 07/08/2014.
 */
public class ProjectDetailEvent extends ReadEvent {
    private int key;
    private ProjectDetail projectDetail;
    private boolean parentFound = true;

    public ProjectDetailEvent(int key, ProjectDetail projectDetail) {
        this.key = key;
        this.projectDetail = projectDetail;
    }

    public boolean isParentFound() {
        return parentFound;
    }

    public ProjectDetailEvent(int key) {
        this.key = key;
    }

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public int getKey() {
        return key;
    }

    public static ProjectDetailEvent notFound(int key){
        ProjectDetailEvent projectDetailEvent = new ProjectDetailEvent(key);
        projectDetailEvent.entityFound = false;
        return projectDetailEvent;
    }

    public static ProjectDetailEvent parentNotFound(int key){
        ProjectDetailEvent projectDetailEvent = new ProjectDetailEvent(key);
        projectDetailEvent.parentFound = false;
        return projectDetailEvent;
    }
}
