package com.flzssolutionsgmbh.projecttimebookingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user-profile")
public class UserProfileController {

    @GetMapping
    public String getUserProfilePage() {
        return "userprofile.html";
    }

}

