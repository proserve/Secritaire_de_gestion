package com.andima.secritaire.core.event.project;


import com.andima.secritaire.core.event.UpdateEvent;

public class SetProjectParentEvent extends UpdateEvent {
    private int key;
    private ProjectDetail projectDetail;

    public SetProjectParentEvent(int key, ProjectDetail projectDetail) {
        this.key = key;
        this.projectDetail = projectDetail;
    }

    public int getKey() {
        return key;
    }

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

}
