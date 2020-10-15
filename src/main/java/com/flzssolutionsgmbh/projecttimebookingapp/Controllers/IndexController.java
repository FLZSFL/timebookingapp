package com.flzssolutionsgmbh.projecttimebookingapp.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /*Responsable for the homepage*/
    @GetMapping("/")
    public String showIndexPage(){
        return "index";
    }

}
