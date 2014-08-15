package com.andima.secritaire.core.transaction;

import com.andima.secritaire.core.event.RequestEvent;
import com.andima.secritaire.core.event.ResponseEvent;
import com.andima.secritaire.core.service.ProjectPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by proserve on 06/08/2014.
 */

public abstract class Transaction{
    private RequestEvent request;
    @Autowired
    ProjectPersistenceService projectPersistenceService;
    protected Transaction(RequestEvent request) {
        this.request = request;
    }

    public RequestEvent getRequest() {
        return request;
    }

    public abstract ResponseEvent execute();
}
