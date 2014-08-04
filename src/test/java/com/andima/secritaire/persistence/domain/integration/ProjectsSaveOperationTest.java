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
import java.util.ArrayList;
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
    }


    @Test
    public void whenISaveProjectWithParent_works() throws Exception {
        requirements.setParentProject(createSoftware);
        Project savedRequirements = projectsRepository.save(requirements);

        assertEquals(savedRequirements.getParentProject(), createSoftware);

        assertEquals(createSoftware.getChildrenProjects().get(0), requirements);
        assertEquals(createSoftware.getChildrenProjects().size(), 1);
    }

    @Test
    public void whenISaveProjectWithChildren_works() throws Exception {
        List<Project> projects = new ArrayList<Project>();
        projects.add(requirements);
        projects.add(development);

        requirements.setParentProject(createSoftware);
        development.setParentProject(createSoftware);

        Project savedCreateSoftware = projectsRepository.save(createSoftware);

        assertEquals(savedCreateSoftware.getChildrenProjects().size(), projects.size());
        assertEquals(savedCreateSoftware.getChildrenProjects().get(0), projects.get(0));
        assertEquals(savedCreateSoftware.getChildrenProjects().get(1), projects.get(1));

        assertEquals(requirements.getParentProject(), createSoftware);
    }

}
