package com.andima.secritaire.persistence.repository;

import com.andima.secritaire.persistence.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectsRepository extends JpaRepository<Project, Integer> {
}
