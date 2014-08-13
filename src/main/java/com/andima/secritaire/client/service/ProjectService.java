package com.andima.secritaire.client.service;

import com.andima.secritaire.client.domain.Project;

import java.util.List;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
public interface ProjectService {
    public List<Project> getAllProject();
    public Project getProjectParent(int key);
    public Project create(Project project);
}
