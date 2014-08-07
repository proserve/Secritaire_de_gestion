package com.andima.secritaire.persistence.service;


import com.andima.secritaire.core.domain.Project;

import java.util.List;

public interface ProjectService {
    public void  create(Project project);
    public void delete(Project project);
    public void deleteById(Integer id);
    public List<Project> findProjectChildren(Project project);

    List<com.andima.secritaire.persistence.domain.Project> findProjectChildren(com.andima.secritaire.persistence.domain.Project project);

    public List<Project> findProjectChildrenById(Integer id);
    public List<Project> findProjectByName(String name);

}
