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
@TransactionConfiguration(defaultRollback = false)
public class ProjectsRepositoryIntegrationTest {
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
        projectsRepository.save(requirements);
        development.setParentProject(createSoftware);
        projectsRepository.save(development);
    }


    @Test
    public void findParentProject_works() throws Exception {

        List<Project> ChildrenProjects = projectsRepository.findByParentProject(createSoftware);
        assertEquals(2, ChildrenProjects.size());
        assertEquals(requirements, ChildrenProjects.get(0));
        assertEquals(development, ChildrenProjects.get(1));
    }



}
