package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**/
@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    private String variable;


    public void addProject(Project project){

        //add additional if statement to ensure that the project is not existing yet
        projectRepository.save(project);
    }

    public void deleteProject(Project project){
        projectRepository.delete(project);
    }

    public void updateProject(Project project, Long id){
        projectRepository.findById(id);
    }

    public void updateProject(Project project, String name){
        projectRepository.findByName(project.getName());
    }

    //Returning all projects in the DB
    public List<Project> getAllProjects(List<Project> projects){
        return projectRepository.findAll(projects);
    }

    //Find all projects defined by User
    public List<Project> findUserProject(User user){
        return projectRepository.findByUser(user);
    }

}

