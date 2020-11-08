package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Email
    @NotEmpty
    private String email;
    private String fn;
    private String ln;
    private String adress;
    private String city;
    private String country;

    @org.springframework.data.annotation.Transient //will not be serialized
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projectList;


    @ManyToMany
    private List<Role> roles;


    public User() {

    }


    public User(String email, String fn, String ln, String password) {
        this.email = email;
        this.fn = fn;
        this.ln = ln;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }


    public String getPassword() {
        String transientPassword = this.password;
        this.password = null;
        return transientPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

