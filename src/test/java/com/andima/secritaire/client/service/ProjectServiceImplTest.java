package com.andima.secritaire.client.service;


import com.andima.secritaire.client.config.ServiceConfig;
import com.andima.secritaire.client.domain.Project;
import com.andima.secritaire.core.event.project.*;
import com.andima.secritaire.persistence.PersistenceFixture;
import com.andima.secritaire.core.service.ProjectPersistenceService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectServiceImplTest {
    @Autowired
    ProjectPersistenceService projectPersistenceService;
    @Autowired
    ProjectService projectService;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRequestAllProject() throws Exception {
        List<Project> projectList = projectService.getAllProject();
        Assert.assertEquals(5, projectList.size());
    }
    
    @Test
    public void testRequestProjectParent() throws Exception{
        CreateProjectEvent parentEvent = new CreateProjectEvent(PersistenceFixture.createProjectDetail("Parent"));
        ProjectCreatedEvent createdParent = projectPersistenceService.createProject(parentEvent);

        CreateProjectEvent childrenEvent = new CreateProjectEvent(PersistenceFixture.createProjectDetail("Children"));
        ProjectCreatedEvent createdChildren = projectPersistenceService.createProject(childrenEvent);

        projectPersistenceService.setProjectParent(new SetProjectParentEvent(createdChildren.getKey(), createdParent.getKey()));

        Project projectParent = projectService.getProjectParent(createdChildren.getKey());

        assertEquals(createdParent.getKey(), projectParent.getId());
    }

    @Test
    public void testProjectCreate() throws Exception{
        String name = "project2";
        Project project = projectService.create(new Project(new Date(), new Date(), name));
        RequestProjectDetailEvent detailsEvent = new RequestProjectDetailEvent(project.getId());
        ProjectDetail projectDetail = projectPersistenceService.requestProjectDetails(detailsEvent).getProjectDetail();
        assertNotNull(projectDetail);

    }

    @Test
    public void testDeleteProject() throws Exception{
        String name = "project2";
        Project project = projectService.create(new Project(new Date(), new Date(), name));
        ProjectDeletedEvent deletedEvent= projectService.delete(project.getId());
        assertEquals(project.getId(), deletedEvent.getKey());
    }

    @Test
    public void testProjectUpdate_parent() throws Exception{
        Project parent = new Project(new Date(), new Date(), "parent2");

        Project children = new Project(new Date(), new Date(), "children2");

        Project savedParent = projectService.create(parent);
        projectService.create(children);

        children.setParentProject(parent);

        ProjectUpdatedEvent updatedProject = projectService.update(children);

        int id = children.getId();
        System.out.println("is n===>" + id);
       // assertEquals(savedParent.getId(), projectService.getProjectParent(updatedProject.getKey()).getId());
        projectService.delete(savedParent.getId());
        projectService.delete(children.getId());
    }
}