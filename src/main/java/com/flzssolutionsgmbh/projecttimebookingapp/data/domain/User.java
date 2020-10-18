package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private String email;
    private String fn;
    private String ln;
    private String password;
    private String company;

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
        return password;
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

