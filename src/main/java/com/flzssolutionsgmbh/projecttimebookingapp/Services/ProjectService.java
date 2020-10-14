package com.flzssolutionsgmbh.projecttimebookingapp.Services;

import com.flzssolutionsgmbh.projecttimebookingapp.Entities.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.Entities.User;
import com.flzssolutionsgmbh.projecttimebookingapp.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;



    public void addProject(Project project, User user){
        project.setUser(user);
        projectRepository.save(project);
    }

    public List<Project> findUserProject(User user){

        return projectRepository.findByUser(user);
    }

}
