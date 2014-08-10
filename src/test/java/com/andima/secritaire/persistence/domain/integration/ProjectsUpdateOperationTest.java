package com.andima.secritaire.persistence.domain.integration;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.PersistenceFixture;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectsUpdateOperationTest {
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


    }

    @After
    public void tearDown() throws Exception {
        projectsRepository.deleteAll();

    }

    private void saveAllProject() {
        projectsRepository.save(createSoftware);
        projectsRepository.save(requirements);
        projectsRepository.save(development);
    }

    @Test
    public void whenIUpdateParentProject_works() throws Exception {
        saveAllProject();
        List<Project> createSoftwareChildren = projectsRepository.findByParentProject(createSoftware);
        assertEquals(requirements.getId(), createSoftwareChildren.get(0).getId());

        requirements.setParentProject(development);
        projectsRepository.save(requirements);

        createSoftwareChildren = projectsRepository.findByParentProject(createSoftware);
        assertNotEquals(requirements.getId(), createSoftwareChildren.get(0).getId());
        List<Project> developmentChildren = projectsRepository.findByParentProject(development);
        assertEquals(requirements.getId(), developmentChildren.get(0).getId());
    }
}
