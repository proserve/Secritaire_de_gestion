package com.andima.secritaire.persistence.service;

import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProjectServiceImpl implements ProjectService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsRepository.class);
    @Override
    public void save(Project project) {

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Project> findProjectChildren(Project project) {
        return null;
    }

    @Override
    public List<Project> findProjectChildrenById(Integer id) {
        return null;
    }

    @Override
    public List<Project> findProjectByName(String name) {
        return null;
    }
}
