package com.andima.secritaire.core.transaction;

import com.andima.secritaire.core.event.ResponseEvent;
import com.andima.secritaire.core.event.project.RequestProjectDetailEvent;

/**
 * Created by GHIBOUB Khalid  on 14/08/2014.
 */
public class addProjectTransaction extends Transaction {

    public addProjectTransaction(RequestProjectDetailEvent request) {
        super(request);
    }

    @Override
    public ResponseEvent execute() {
        return null;
    }
}