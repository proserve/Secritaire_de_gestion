package com.andima.secritaire.core.event.project;


import com.andima.secritaire.core.event.requestEvent.UpdateEvent;

import java.util.List;

public class SetProjectChildrenEvent extends UpdateEvent {
    private int key;
    private List<ProjectDetail> projectDetailList;

    public SetProjectChildrenEvent(int key, List<ProjectDetail> projectDetailList) {
        this.key = key;
        this.projectDetailList = projectDetailList;
    }

    public int getKey() {
        return key;
    }

    public List<ProjectDetail> getProjectDetailList() {
        return projectDetailList;
    }
}
