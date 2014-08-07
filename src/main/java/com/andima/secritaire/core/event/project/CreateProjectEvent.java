package com.andima.secritaire.core.event.project;


import com.andima.secritaire.core.event.CreateEvent;

public class CreateProjectEvent extends CreateEvent {
  private ProjectDetail details;

  public CreateProjectEvent(ProjectDetail details) {
    this.details = details;
  }

  public ProjectDetail getDetails() {
    return details;
  }
}
