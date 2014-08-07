package com.andima.secritaire.persistence.domain.service;

import com.andima.secritaire.persistence.repository.ProjectsRepository;
import com.andima.secritaire.persistence.service.ProjectService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
public class ProjectServiceTest {

    public static final String OLD_PROJECT_NAME = "development";
    ProjectsRepository projectRepositoryMock;
    private ProjectService projectService;

    @Before
    public void setUp() throws Exception {
        projectRepositoryMock = mock(ProjectsRepository.class);
    }

    @Test
    public void saveMethod() throws Exception{
    }
}
