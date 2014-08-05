package com.andima.secritaire.persistence.domain.integration;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.domain.fixture.PersistenceFixture;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectsDeleteOperationTest {
    Project createSoftware;
    Project requirements;
    Project development;
    @Autowired
    ProjectsRepository projectsRepository;

    @Before
    public void setUp() throws Exception {
        createSoftware = PersistenceFixture.createProject("create a new software");
        requirements =  PersistenceFixture.createProject("collect specifications");
        development = PersistenceFixture.createProject("development phase");

        requirements.setParentProject(createSoftware);
        development.setParentProject(createSoftware);

        projectsRepository.save(createSoftware);
        projectsRepository.save(requirements);
        projectsRepository.save(development);
    }

    @Test
    public void whenIDeleteAProject_AllChildrenShouldBeDeleted() throws Exception {
        assertNotNull(projectsRepository.findOne(createSoftware.getId()));
        assertNotNull(projectsRepository.findOne(requirements.getId()));
        assertNotNull(projectsRepository.findOne(development.getId()));

        projectsRepository.delete(createSoftware);
        assertNull(projectsRepository.findOne(createSoftware.getId()));
        assertNull(projectsRepository.findOne(requirements.getId()));
        assertNull(projectsRepository.findOne(development.getId()));

    }
}
