package com.andima.secritaire.client.ClientTest.service;

import com.andima.secritaire.client.ClientTest.domain.Project;

import java.util.List;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
public interface ProjectService {
    public List<Project> requestAllProject();
    public Project getProjectParent(int key);
    public Project create(Project project);
}
