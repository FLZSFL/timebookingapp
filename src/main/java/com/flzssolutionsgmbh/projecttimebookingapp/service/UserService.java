package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Role;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }


    public void createAdmin(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    //Credit to https://stackoverflow.com/questions/12974322/does-anyone-know-a-library-containing-all-localized-country-names-in-java/12975050
    public void generateCountries(){
        Locale locale = Locale.ENGLISH;
        for (String country : Locale.getISOCountries()){
            System.out.println(new Locale("", country).getDisplayCountry(locale));
        }
        System.out.println("Not Listed");
    }





}

