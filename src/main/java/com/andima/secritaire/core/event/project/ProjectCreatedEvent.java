package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.CreatedEvent;

public class ProjectCreatedEvent extends CreatedEvent {

  private final int Key;
  private final ProjectDetail details;

  public ProjectCreatedEvent(final int Key, final ProjectDetail details) {
    this.Key = Key;
    this.details = details;
  }

  public ProjectDetail getDetails() {
    return details;
  }

  public int getKey() {
    return Key;
  }
}
