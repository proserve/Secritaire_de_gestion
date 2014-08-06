package com.andima.secritaire.persistence.repository;

import com.andima.secritaire.persistence.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectsRepository extends JpaRepository<Project, Integer> {
    public List<Project> findByParentProject(Project project);
}