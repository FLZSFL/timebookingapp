package com.flzssolutionsgmbh.projecttimebookingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    @GetMapping
    public String getRegisterPage(){
        return "register.html";
    }

}