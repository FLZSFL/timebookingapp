package com.flzssolutionsgmbh.projecttimebookingapp.api;


import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.service.ProjectService;
import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ProjectEndpoint {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    //Getting all projects through API request
    @GetMapping(path = "/projects", produces = "application/json")
    public List<Project> projects() {
        return projectService.getAllProjects();
    }


    @PostMapping(path = "/book-project", produces = "application/json")
    public Project bookProject(@RequestParam Long projectId, @RequestParam Long hours) {

        Project project = projectService.findProject(projectId);
        project.setTimeSpent(project.getTimeSpent() + hours);
        projectService.saveProject(project);

        return project;
    }


    //Importing the database of the projects
    @GetMapping(path = "/create-projects", produces = "application/json")
    public String createProjects() {

        for(int i=0; i<100; i++) {
            Project p1 = new Project();
            p1.setName("Project 1");
            p1.setStartTime(Date.valueOf("2020-11-10"));
            p1.setEndTime(Date.valueOf("2020-12-10"));
            p1.setTimeSpent(0L);
            p1.setActivityDescription("Description");
            projectService.addProject(p1);
        }

        return "Done";
    }



}
