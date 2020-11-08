package com.flzssolutionsgmbh.projecttimebookingapp.api;


import com.flzssolutionsgmbh.projecttimebookingapp.service.ProjectService;
import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ProjectEndpoint {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;






}
