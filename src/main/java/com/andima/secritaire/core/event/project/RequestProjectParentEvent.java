package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.ReadEvent;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
public class RequestProjectParentEvent extends ReadEvent{
    private int key;

    public RequestProjectParentEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
