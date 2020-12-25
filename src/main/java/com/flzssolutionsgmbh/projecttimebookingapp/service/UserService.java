package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Role;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("USER PASSWORD: " + user.getRegistrationPassword());

        System.out.println("ENCODED=" + encoder.encode(user.getRegistrationPassword()));
        user.setPassword(encoder.encode(user.getRegistrationPassword()));

        Role userRole = new Role(Role.RoleName.USER.toString());

        userRole.setUser(user);
        user.addRole(userRole);
        userRepository.save(user);
    }


    public void createAdmin(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getRegistrationPassword()));
        Role userRole = new Role(Role.RoleName.ADMIN.toString());
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

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void changePassword(User user, String password) {
        System.out.println("Changing password to " + password.toString() + " for user " + user.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        System.out.println("ENCODED=" + encoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        System.out.println("Finding user by email " + email);
        if(user == null) {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }

        return user;
    }

}

