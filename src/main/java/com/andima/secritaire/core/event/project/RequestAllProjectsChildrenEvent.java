package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.requestEvent.RequestReadEvent;

/**
 * Created by proserve on 10/08/2014.
 */
public class RequestAllProjectsChildrenEvent extends RequestReadEvent {
    private int key;

    public RequestAllProjectsChildrenEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
