package com.flzssolutionsgmbh.projecttimebookingapp.config;

import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*WebSecurityConfigurerAdapter enables the extension of HTTPSecurity method*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;



    /* Authenticating that every request to the application is authenticated
    * with form based login*/

    /*
    * register, api-register-user and other pages should be available for everyone
    * /assets/** are publicly available to ensure the loading of CSS
    * the commands hasRole is not used, since the app is thought for Admin and User (both), thus
    * there should be no distinction of both, as only the page /projects should be accessible differently for each
    userrole*/

    /*HTTP Security regulating the HTTP Accesses*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/api/register-user", "/console/**", "/api/create-projects").permitAll()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .csrf().disable()
                .logout()
                .permitAll();
    }

    /*Password encoder is mandatory for the newest versions of SpringBoot
    * to ensure working of the SpringBoot with Security Component*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Regulating the role accesses, in our case through userService*/
    /*Encoding the password, so it is not recognizable*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}






