package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.DeletedEvent;

/**
 * Created by proserve on 07/08/2014.
 */
public class ProjectDeletedEvent extends DeletedEvent {
    private int key;
    private ProjectDetail detail;
    private boolean deletionCompleted;

    public ProjectDeletedEvent(int key, ProjectDetail detail) {
        this.key = key;
        this.detail = detail;
        this.deletionCompleted = true;
    }

    public ProjectDeletedEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public ProjectDetail getDetail() {
        return detail;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static ProjectDeletedEvent DeletionForbiden(int key, ProjectDetail projectDetail){
        ProjectDeletedEvent deletedEvent = new ProjectDeletedEvent(key, projectDetail);
        deletedEvent.deletionCompleted = false;
        deletedEvent.entityFound = true;
        return deletedEvent;
    }

    public static ProjectDeletedEvent notFound(int key) {
        ProjectDeletedEvent ev = new ProjectDeletedEvent(key);
        ev.entityFound=false;
        return ev;
    }
}
