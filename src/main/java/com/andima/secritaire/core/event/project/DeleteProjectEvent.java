package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.requestEvent.DeleteEvent;

/**
 * Created by proserve on 07/08/2014.
 */
public class DeleteProjectEvent extends DeleteEvent {
    private int key;

    public DeleteProjectEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
