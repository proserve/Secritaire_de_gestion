package com.andima.secritaire.client.service;

import com.andima.secritaire.client.domain.Project;
import com.andima.secritaire.core.event.project.*;
import com.andima.secritaire.core.service.ProjectPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectPersistenceService projectPersistenceService;
    @Override
    public List<Project> getAllProject() {
        RequestAllProjectsEvent allProjectsEvent = new RequestAllProjectsEvent();
        AllProjectsEvent allProjects = projectPersistenceService.requestAllProjects(new RequestAllProjectsEvent());
        return  getProjectListFromAllProjectEvent(allProjects);
    }

    @Override
    public Project getProjectParent(int key) {
        ProjectDetailEvent detailEvent = projectPersistenceService.requestParentProjectDetail(new RequestProjectParentEvent(key));
        Project project = Project.fromProjectDetails(detailEvent.getProjectDetail());
        project.setId(detailEvent.getKey());
        return project;
    }

    @Override
    public Project create(Project project) {
        ProjectCreatedEvent createdEvent = projectPersistenceService.createProject(new CreateProjectEvent(project.toProjectDetails()));
        Project resultProject = Project.fromProjectDetails(createdEvent.getDetails());
        resultProject.setId(createdEvent.getKey());
        return resultProject;
    }

    private List<Project> getProjectListFromAllProjectEvent( AllProjectsEvent allProjects) {
            List<Project> projectList = new ArrayList<Project>();
            Map<Integer, ProjectDetail> projectDetailMap = allProjects.getProjects();
            for (Integer key : projectDetailMap.keySet()) {
                Project project = Project.fromProjectDetails(projectDetailMap.get(key));
                project.setId(key);
                projectList.add(project);
            }
            return projectList;
        }



}
