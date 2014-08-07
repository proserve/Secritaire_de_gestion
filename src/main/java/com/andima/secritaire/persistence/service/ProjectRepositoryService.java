package com.andima.secritaire.persistence.service;

import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProjectRepositoryService implements ProjectService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsRepository.class);

    @Override
    public void create(com.andima.secritaire.core.domain.Project project) {

    }

    @Override
    public void delete(com.andima.secritaire.core.domain.Project project) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<com.andima.secritaire.core.domain.Project> findProjectChildren(com.andima.secritaire.core.domain.Project project) {
        return null;
    }

    @Override
    public List<Project> findProjectChildren(Project project) {
        return null;
    }

    @Override
    public List<com.andima.secritaire.core.domain.Project> findProjectChildrenById(Integer id) {
        return null;
    }

    @Override
    public List<com.andima.secritaire.core.domain.Project> findProjectByName(String name) {
        return null;
    }
}
