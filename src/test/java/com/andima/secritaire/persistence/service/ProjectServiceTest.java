package com.andima.secritaire.persistence.service;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.core.event.project.*;
import com.andima.secritaire.core.service.ProjectPersistenceService;
import com.andima.secritaire.persistence.PersistenceFixture;
import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectServiceTest {

    @Autowired
    private ProjectPersistenceService projectPersistenceService;
    @Autowired
    private ProjectsRepository projectsRepository;

    @Before
    public void setUp() throws Exception {

    }

    private void addSomeProjectToDataBase() {
        for (int i = 0; i < 5; i++) {
            Project project = PersistenceFixture.createProject("project " + i);
            projectsRepository.save(project);
        }
    }

    @After
    public void tearDown() throws Exception {
        projectsRepository.deleteAll();
    }


    @Test
    public void testCreateProject_works() throws Exception {
        CreateProjectEvent createEvent = new CreateProjectEvent(createProjectDetail());
        ProjectCreatedEvent createdEvent = projectPersistenceService.createProject(createEvent);
        Project createdProject = projectsRepository.findOne(createdEvent.getKey());

        assertNotNull(createdProject);
        assertEquals(createdProject.getId().intValue(), createdEvent.getKey());
        assertEquals(createdProject.getName(), createdEvent.getDetails().getName());

    }

    private ProjectDetail createProjectDetail() {
        return PersistenceFixture.createProjectDetail("creation de software");
    }

    @Test
    public void testRequestAllProjects() throws Exception {
        addSomeProjectToDataBase();
        RequestAllProjectsEvent requestAllProjectsEvent = new RequestAllProjectsEvent();
        AllProjectsEvent allProjectsEvent = projectPersistenceService.requestAllProjects(requestAllProjectsEvent);
        for (Integer key : allProjectsEvent.getProjects().keySet()) {
            Project project = projectsRepository.findOne(key);
            assertNotNull(project);
           assertEquals(project.getName(), allProjectsEvent.getProjects().get(key).getName());
        }

    }

    @Test
    public void testRequestProjectDetails_withEntityIsFound() throws Exception {
        Project project = PersistenceFixture.createProject("test for request");
        projectsRepository.save(project);

        int key = project.getId();
        RequestProjectDetailEvent requestProjectDetailEvent = new RequestProjectDetailEvent(key);
        ProjectDetailEvent projectDetailEvent = projectPersistenceService.requestProjectDetails(requestProjectDetailEvent);
        assertEquals(key, projectDetailEvent.getKey());
        assertEquals(project.getName(), projectDetailEvent.getProjectDetail().getName());
        assertTrue(projectDetailEvent.isEntityFound());
    }

    @Test
    public void testRequestProjectDetails_withEntityNotFound() throws Exception {
        int key = 45;
        RequestProjectDetailEvent requestProjectDetailEvent = new RequestProjectDetailEvent(key);
        ProjectDetailEvent projectDetailEvent = projectPersistenceService.requestProjectDetails(requestProjectDetailEvent);
        assertEquals(key, projectDetailEvent.getKey());
        assertNull(projectDetailEvent.getProjectDetail());
        assertFalse(projectDetailEvent.isEntityFound());
    }

    @Test
    public void testSetProjectParent() throws Exception {
        Project project1 = PersistenceFixture.createProject("project 1");
        projectsRepository.save(project1);

        Project project2 = PersistenceFixture.createProject("project 2");
        project2.setParentProject(project1);
        projectsRepository.save(project2);

        Project project3 = PersistenceFixture.createProject("project 3");
        project3.setParentProject(project2);
        projectsRepository.save(project3);

        SetProjectParentEvent setProjectParentEvent = new SetProjectParentEvent(project1.getId(), project3.getId());
        ProjectUpdatedEvent projectUpdatedEvent = projectPersistenceService.setProjectParent(setProjectParentEvent);
        assertEquals(true, projectUpdatedEvent.notValidParent);
    }

    @Test
    public void testDeleteProject() throws Exception {
        Project saved = projectsRepository.save(PersistenceFixture.createProject("test11"));
        DeleteProjectEvent event = new DeleteProjectEvent(saved.getId());
        ProjectDeletedEvent deletedEvent = projectPersistenceService.deleteProject(event);
        assertEquals(saved.getId().intValue(), deletedEvent.getKey());
        assertEquals(saved.getName(), deletedEvent.getDetail().getName());
        assertEquals(true, deletedEvent.isEntityFound());
    }

    @Test
    public void testDeleteProject_notFoundProject() throws Exception {
        int key = 5;
        DeleteProjectEvent event = new DeleteProjectEvent(key);
        ProjectDeletedEvent deletedEvent = projectPersistenceService.deleteProject(event);
        assertEquals(5, deletedEvent.getKey());
        assertEquals(false, deletedEvent.isEntityFound());
        assertEquals(null, deletedEvent.getDetail());
    }

    @Test  @Ignore(value = "to do when there are a problem with database")
    public void testDeleteProject_deletionNotCompleted() throws Exception{
        Project saved = projectsRepository.save(PersistenceFixture.createProject("test14"));
        Project test22 = PersistenceFixture.createProject("test24");
        test22.setParentProject(saved);
        projectsRepository.save(test22);

        DeleteProjectEvent event = new DeleteProjectEvent(saved.getId());
        ProjectDeletedEvent deletedEvent = projectPersistenceService.deleteProject(event);

        assertEquals(saved.getId().intValue(), deletedEvent.getKey());
        assertEquals(saved.getName(), deletedEvent.getDetail().getName());
        assertEquals(true, deletedEvent.isEntityFound());
        assertEquals(false, deletedEvent.isDeletionCompleted());
    }

    @Test
    public void testRequestAllProjectChildren_emptyList() throws Exception{
        Project saved = projectsRepository.save(PersistenceFixture.createProject("test14"));
        RequestAllProjectsChildrenEvent event = new RequestAllProjectsChildrenEvent(saved.getId());
        AllProjectChildrenEvent allProjectChildren = projectPersistenceService.requestAllProjectChildren(event);

        assertEquals(true, allProjectChildren.isEntityFound());
        assertEquals(saved.getName(), allProjectChildren.getProjectDetail().getName());
        assertEquals(true, allProjectChildren.noChildren);
        assertEquals(true, allProjectChildren.getChildren().isEmpty());
    }

    @Test
    public void testRequestAllProjectChildren() throws Exception{
        Project parent = projectsRepository.save(PersistenceFixture.createProject("parent"));
        Project children = PersistenceFixture.createProject("children");
        children.setParentProject(parent);
        projectsRepository.save(children);

        RequestAllProjectsChildrenEvent event = new RequestAllProjectsChildrenEvent(parent.getId());
        AllProjectChildrenEvent allProjectChildren = projectPersistenceService.requestAllProjectChildren(event);

        assertEquals(true, allProjectChildren.isEntityFound());
        assertEquals(1, allProjectChildren.getChildren().size());
        assertEquals(false, allProjectChildren.noChildren);
        assertEquals(parent.getName(), allProjectChildren.getProjectDetail().getName());
        assertEquals(children.getName(), allProjectChildren.getChildren().get(children.getId()).getName());
    }


    @Test
    public void testRequestProjectParent() throws Exception{
        Project parent = projectsRepository.save(PersistenceFixture.createProject("parent"));
        Project children = PersistenceFixture.createProject("children");
        children.setParentProject(parent);
        projectsRepository.save(children);

        RequestProjectParentEvent event = new RequestProjectParentEvent(children.getId());
        ProjectDetailEvent detailEvent = projectPersistenceService.requestParentProjectDetail(event);

        assertEquals(true, detailEvent.isEntityFound());
        assertEquals(parent.getName(), detailEvent.getProjectDetail().getName());
        assertEquals(parent.getId().intValue(), detailEvent.getKey());
    }
    @Test
    public void testRequestProjectParent_noParent() throws Exception{
        Project children = PersistenceFixture.createProject("children");
        projectsRepository.save(children);

        RequestProjectParentEvent event = new RequestProjectParentEvent(children.getId());
        ProjectDetailEvent detailEvent = projectPersistenceService.requestParentProjectDetail(event);

        assertEquals(true, detailEvent.isEntityFound());
        assertEquals(false, detailEvent.isParentFound());
        assertNull(detailEvent.getProjectDetail());
        assertEquals(children.getId().intValue(), detailEvent.getKey());
    }
}
























