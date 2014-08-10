package com.andima.secritaire.desktop.sample;

import com.andima.secritaire.persistence.domain.Project;
import com.andima.secritaire.persistence.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


public class SampleController
{
    @Autowired
    private Person person;
    @Autowired
    ProjectsRepository projectsRepository;

    public Person getPerson()
    {
        return person;
    }

    public void print()
    {
        System.out.println("Well done, " + person.getFirstName() + "!");
        Project project = new Project();
        project.setName("");
        try{
        projectsRepository.save(project);
        }catch(DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }
    }
}