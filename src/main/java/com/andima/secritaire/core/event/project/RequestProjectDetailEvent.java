package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.RequestReadEvent;

/**
 * Created by proserve on 07/08/2014.
 */
public class RequestProjectDetailEvent extends RequestReadEvent {
    private int key;

    public RequestProjectDetailEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
