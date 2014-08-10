package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.ReadEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by proserve on 10/08/2014.
 */
public class AllProjectChildrenEvent extends ReadEvent {
    private int  key;
    private ProjectDetail projectDetail;
    private Map<Integer, ProjectDetail> children = new HashMap<Integer, ProjectDetail>();
    public boolean noChildren = false;

    public AllProjectChildrenEvent(int key) {
        this.key = key;
    }

    public AllProjectChildrenEvent(int key, ProjectDetail projectDetail) {
        this.key = key;
        this.projectDetail = projectDetail;
    }

    public AllProjectChildrenEvent(int key, ProjectDetail projectDetail, Map<Integer, ProjectDetail> children) {
        this.key = key;
        this.projectDetail = projectDetail;
        this.children = children;
    }

    public int getKey() {
        return key;
    }

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public Map<Integer, ProjectDetail> getChildren() {
        return children;
    }

    public static AllProjectChildrenEvent notFound(int key){
        AllProjectChildrenEvent event = new AllProjectChildrenEvent(key);
        event.entityFound = false;
        return event;
    }

    public static AllProjectChildrenEvent noChildren(int key, ProjectDetail projectDetail){
        AllProjectChildrenEvent event = new AllProjectChildrenEvent(key, projectDetail);
        event.noChildren = true;
        return event;
    }
}
