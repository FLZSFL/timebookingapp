package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*Test*/
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private String variable;


    public void addProject(Project project, User user){
        project.setUser(user);
        projectRepository.save(project);
    }

    public List<Project> findUserProject(User user){

        return projectRepository.findByUser(user);
    }

}

