package com.andima.secritaire.persistence.domain.fixture;

import com.andima.secritaire.persistence.domain.Project;

import java.util.Date;

/**
 * Created by proserve on 03/08/14.
 */
public class PersistenceFixture {
    public static Project createProject(String name) {
        Project project = new Project();
        project.setName(name);
        project.setStartDate(new Date());
        project.setEndDate(new Date(2014, 10, 10));
        return project;
    }
}
