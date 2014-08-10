package com.andima.secritaire.core.event.responseEvent;

import com.andima.secritaire.core.event.ResponseEvent;

public class DeletedEvent implements ResponseEvent {
  protected boolean entityFound = true;

  public boolean isEntityFound() {
    return entityFound;
  }
}
