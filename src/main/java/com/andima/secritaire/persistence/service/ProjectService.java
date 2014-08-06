package com.andima.secritaire.persistence.service;


import com.andima.secritaire.persistence.domain.Project;

import java.util.List;

public interface ProjectService {
    public void  save(Project project);
    public void delete(Project project);
    public void deleteById(Integer id);
    public List<Project> findProjectChildren(Project project);
    public List<Project> findProjectChildrenById(Integer id);
    public List<Project> findProjectByName(String name);

}
