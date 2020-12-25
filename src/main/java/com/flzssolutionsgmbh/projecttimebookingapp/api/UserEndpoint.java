package com.flzssolutionsgmbh.projecttimebookingapp.api;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api")
public class UserEndpoint {


    @Autowired
    private UserService userService;

    @PostMapping(path = "/register-user", produces = "application/json")
    public User registerUser(@RequestBody User newUser) {
        userService.createUser(newUser);
        return newUser;
    }

    @GetMapping(path = "/user", produces = "application/json")
    public User user(HttpServletRequest request) {
        return (User)userService.loadUserByUsername(request.getRemoteUser());
    }

    @PutMapping(path = "/user", produces = "application/json")
    public User updateUser(HttpServletRequest request, @RequestBody User updatedUser) {
        User user = (User)userService.loadUserByUsername(request.getRemoteUser());

        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        userService.updateUser(user);

        return user;
    }

    @PutMapping(path = "/user-info", produces = "application/json")
    public User updateUserInfo(HttpServletRequest request, @RequestBody User updatedUser) {
        User user = (User)userService.loadUserByUsername(request.getRemoteUser());

        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setCountry(updatedUser.getCountry());
        userService.updateUser(user);

        return user;
    }

    @PostMapping(path = "/change-password", produces = "application/json")
    public User changePassword(HttpServletRequest request, @RequestParam String password) {

        System.out.println("CLASS=" + password.getClass());

        User user = (User)userService.loadUserByUsername(request.getRemoteUser());
        userService.changePassword(user, password);
        return null;
    }
}

