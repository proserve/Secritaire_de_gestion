package com.andima.secritaire.persistence.service;


import com.andima.secritaire.core.event.project.*;

public interface ProjectPersistenceService {

  public AllProjectsEvent requestAllProjects(RequestAllProjectsEvent requestAllCurrentOrdersEvent);

  public ProjectDetailEvent requestProjectDetails(RequestProjectDetailEvent requestProjectDetailsEvent);

  public ProjectCreatedEvent createProject(CreateProjectEvent event);

  public ProjectUpdatedEvent setProjectParent(SetProjectParentEvent setOrderPaymentEvent);

  public ProjectDeletedEvent deleteProject(DeleteProjectEvent deleteOrderEvent);

  public AllProjectChildrenEvent requestAllProjectChildren(RequestAllProjectsChildrenEvent projectChildrenEvent);

  public ProjectDetailEvent requestParentProjectDetail(RequestProjectParentEvent parentEvent);

}
