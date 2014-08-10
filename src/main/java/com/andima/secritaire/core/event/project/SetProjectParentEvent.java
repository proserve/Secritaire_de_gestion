package com.andima.secritaire.core.event.project;


import com.andima.secritaire.core.event.requestEvent.UpdateEvent;

public class SetProjectParentEvent extends UpdateEvent {
    private int key;
    private int parentKey;

    public SetProjectParentEvent(int key, int parentKey) {
        this.key = key;
        this.parentKey = parentKey;
    }

    public int getKey() {
        return key;
    }

    public int getParentKey() {
        return parentKey;
    }
}