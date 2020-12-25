package com.flzssolutionsgmbh.projecttimebookingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/projects")
public class ProjectsController {

    @GetMapping
    public String getProjectPage(){
        return "projectpage.html";
    }

}
