package com.andima.secritaire.persistence.domain.integration;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.persistence.PersistenceFixture;
import com.andima.secritaire.persistence.domain.Project;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

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
    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        createSoftware = PersistenceFixture.createProject("create a new software");
        requirements = PersistenceFixture.createProject("collect specifications");
        development = PersistenceFixture.createProject("development phase");

        requirements.setParentProject(createSoftware);
        development.setParentProject(createSoftware);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         validator = factory.getValidator();
    }

    @After
    public void tearDown() throws Exception {
        projectsRepository.deleteAll();
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

    @Test
    public void whenISaveProjectIsParentOfHisSelf() throws Exception {
        requirements.setParentProject(requirements);
        projectsRepository.save(requirements);

        List<Project> byParentProject = projectsRepository.findByParentProject(requirements);

        assertEquals(requirements.getParentProject(), requirements);
        assertEquals(1, byParentProject.size());
        assertEquals(requirements.getId(), byParentProject.get(0).getId());
    }

    @Test
    public void validationRules() throws Exception {
       Project project = new Project();
        Set<ConstraintViolation<Project>> constraintViolations =
                validator.validateProperty(project, "name");
       assertEquals(Project.NAME_BLANK_VALIDATION_MESSAGE, constraintViolations.iterator().next().getMessage());

        constraintViolations = validator.validateProperty(project, "startDate");
        assertEquals(Project.START_DATE_NULL_VALIDATION_MESSAGE, constraintViolations.iterator().next().getMessage());

        constraintViolations = validator.validateProperty(project, "endDate");
        assertEquals(Project.END_DATE_NULL_VALIDATION_MESSAGE, constraintViolations.iterator().next().getMessage());
    }

}
