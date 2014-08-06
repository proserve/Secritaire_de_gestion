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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProjectsSaveOperationTest {
    Project createSoftware;
    Project requirements;
    Project development;
    @Autowired
    ProjectsRepository projectsRepository;

    @Before
    public void setUp() throws Exception {
        createSoftware = PersistenceFixture.createProject("create a new software");
        requirements = PersistenceFixture.createProject("collect specifications");
        development = PersistenceFixture.createProject("development phase");

        requirements.setParentProject(createSoftware);
        development.setParentProject(createSoftware);
    }


    @Test
    public void whenISaveProjectWithParent_works() throws Exception {
        projectsRepository.save(createSoftware);
        projectsRepository.save(requirements);
        projectsRepository.save(development);
        List<Project> byParentProject = projectsRepository.findByParentProject(createSoftware);

        assertEquals(requirements.getParentProject(), createSoftware);
        assertEquals(development.getParentProject(), createSoftware);
        assertEquals(2, byParentProject.size());
        assertEquals(requirements.getId(), byParentProject.get(0).getId());
        assertEquals(development.getId(), byParentProject.get(1).getId());
    }
}
