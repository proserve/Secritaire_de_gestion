package com.andima.secritaire.persistence.service;

import com.andima.secritaire.core.event.project.*;
import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectPersistenceEventHandler implements ProjectPersistenceService {

    @Autowired
    private ProjectsRepository projectsRepository;
    boolean isValidParent = false;
    Logger logger = LoggerFactory.getLogger(ProjectsRepository.class);

    public ProjectCreatedEvent createProject(CreateProjectEvent createProjectEvent) {
        Project project = Project.fromProjectDetails(createProjectEvent.getDetails());
        project = projectsRepository.save(project);
        logger.info("project with (name = "+project.getName()+" || id = "+project.getId()+") was successfully created");
        return new ProjectCreatedEvent(project.getId(), project.toProjectDetails());
    }

    public AllProjectsEvent requestAllProjects(RequestAllProjectsEvent requestAllCurrentProjectsEvent) {
        List<Project> projectsRepositoryAll = projectsRepository.findAll();
        Map<Integer, ProjectDetail> generatedDetails = getProjectDetailMap(projectsRepositoryAll);
        logger.info("All projects was successfully fetched (there are "+generatedDetails.size()+
                " projects) in database");
        return new AllProjectsEvent(generatedDetails);
    }

        private Map<Integer, ProjectDetail> getProjectDetailMap(List<Project> projectsRepositoryAll) {
            Map<Integer, ProjectDetail> generatedDetails = new HashMap<Integer, ProjectDetail>();
            for (Project project : projectsRepositoryAll) {
                generatedDetails.put(project.getId(), project.toProjectDetails());
            }
            return generatedDetails;
        }

    public ProjectDetailEvent requestProjectDetails(RequestProjectDetailEvent requestProjectDetailsEvent) {
        Project project = projectsRepository.findOne(requestProjectDetailsEvent.getKey());
        if (project == null) {
            logger.error("project with (id = " + requestProjectDetailsEvent.getKey() + ") Not found in the database");
            return ProjectDetailEvent.notFound(requestProjectDetailsEvent.getKey());
        }
        logger.info("project with (name = " + project.getName() + " || id = " + project.getId() + ") was successfully fetched");
        return new ProjectDetailEvent(
                requestProjectDetailsEvent.getKey(),
                project.toProjectDetails());
    }

    public ProjectUpdatedEvent setProjectParent(SetProjectParentEvent setProjectParentEvent) {
        Project project = projectsRepository.findOne(setProjectParentEvent.getKey());
        Project parentProject = projectsRepository.findOne(setProjectParentEvent.getParentKey());
        return getProjectUpdatedEvent(setProjectParentEvent, project, parentProject);
    }

        private ProjectUpdatedEvent getProjectUpdatedEvent(SetProjectParentEvent setProjectParentEvent, Project project,
                                                           Project parentProject) {
            if (project == null || parentProject == null) {
                return ProjectUpdatedEvent.notFound(setProjectParentEvent.getKey());
            }
            else if (isTheAValidParent(project, setProjectParentEvent.getParentKey())) {
                return ProjectUpdatedEvent.sameParent(setProjectParentEvent.getKey());
            }

            updateProject(project, parentProject);
            return new ProjectUpdatedEvent(project.getId(), project.toProjectDetails());
        }

            private void updateProject(Project project, Project parentProject) {
        project.setParentProject(parentProject);
        projectsRepository.save(project);
    }

            private boolean isTheAValidParent(Project project, int parentKey) {
                return isThisParentExciteInChildren(project, parentKey);
            }

    public ProjectDeletedEvent deleteProject(DeleteProjectEvent deleteProjectEvent) {
        Project project = projectsRepository.findOne(deleteProjectEvent.getKey());
        if (project == null) {
            return ProjectDeletedEvent.notFound(deleteProjectEvent.getKey());
        }
        return getProjectDeletedEvent(deleteProjectEvent, project);
    }

        private ProjectDeletedEvent getProjectDeletedEvent(DeleteProjectEvent deleteProjectEvent, Project project) {
            try {
                projectsRepository.delete(deleteProjectEvent.getKey());
                return new ProjectDeletedEvent(deleteProjectEvent.getKey(), project.toProjectDetails());
            }catch (Exception e){
                return  ProjectDeletedEvent.DeletionForbiden(deleteProjectEvent.getKey(), project.toProjectDetails());
            }
        }

    public AllProjectChildrenEvent requestAllProjectChildren(RequestAllProjectsChildrenEvent projectChildrenEvent) {
        Project parentProject = projectsRepository.findOne(projectChildrenEvent.getKey());
        int key = projectChildrenEvent.getKey();
        return getAllProjectChildrenEvent(parentProject, key);
    }

    @Override
    public ProjectDetailEvent requestParentProjectDetail(RequestProjectParentEvent parentEvent) {
        Project children = projectsRepository.findOne(parentEvent.getKey());
        if (children == null) {
            return ProjectDetailEvent.notFound(children.getId());
        }
        Project parent = children.getParentProject();

        if (parent == null) {
            return ProjectDetailEvent.parentNotFound(children.getId());
        }
        return new ProjectDetailEvent(parent.getId(), parent.toProjectDetails());
    }

    private AllProjectChildrenEvent getAllProjectChildrenEvent(Project parentProject, int key) {
            if(parentProject == null) return AllProjectChildrenEvent.notFound(key);
            List<Project> children = projectsRepository.findByParentProject(parentProject);
            if(children.isEmpty()) return AllProjectChildrenEvent.noChildren(key, parentProject.toProjectDetails());
            return new AllProjectChildrenEvent(key, parentProject.toProjectDetails(), getProjectDetailMap(children));
        }

    public boolean isThisParentExciteInChildren(Project project, int parentKey) {
        for (Project child : getProjectChildren(project)) {
            if (child.getId() == parentKey) isValidParent = true;
            else isTheAValidParent(child, parentKey);
        }
        return isValidParent;
    }

        private List<Project> getProjectChildren(Project project) {
            List<Project> children = new ArrayList<Project>();
            try {
                children = projectsRepository.findByParentProject(project);
            } catch (NullPointerException e) {}
            return children;
        }


}