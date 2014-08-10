package com.andima.secritaire.persistence.repository;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.PersistenceFixture;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
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
        development.setParentProject(createSoftware);

        projectsRepository.save(createSoftware);
        projectsRepository.save(requirements);
        projectsRepository.save(development);
    }

    @After
    public void tearDown() throws Exception {
        projectsRepository.deleteAll();

    }

    @Test
    public void findParentProject_works() throws Exception {

        List<Project> ChildrenProjects = projectsRepository.findByParentProject(createSoftware);

        assertEquals(2, ChildrenProjects.size());
        assertEquals(requirements.getId(), ChildrenProjects.get(0).getId());
        assertEquals(development.getId(), ChildrenProjects.get(1).getId());
    }

}
