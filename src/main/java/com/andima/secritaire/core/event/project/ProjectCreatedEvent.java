package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.responseEvent.CreatedEvent;

public class ProjectCreatedEvent extends CreatedEvent {

  private final int newProjectKey;
  private final ProjectDetail details;

  public ProjectCreatedEvent(final int newProjectKey, final ProjectDetail details) {
    this.newProjectKey = newProjectKey;
    this.details = details;
  }

  public ProjectDetail getDetails() {
    return details;
  }

  public int getNewProjectKey() {
    return newProjectKey;
  }
}
