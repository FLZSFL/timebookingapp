package com.flzssolutionsgmbh.projecttimebookingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    @GetMapping
    public String getLogoutPage() {
        return "logout.html";
    }
}
