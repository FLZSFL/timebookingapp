package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* UserDetails encapsulates the user information into the auth-objects */
/* Giving the DB table a name */
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;


    /*Unique = cannot be stored twice as the same*/
    @Column(nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;

    //will not be serialized
    @org.springframework.data.annotation.Transient
    @JsonIgnore /* we don't want password to be sent in a JSON response via API */
    private String password;

    //@JsonInclude
    @Transient
    private String registrationPassword;


    /* Referencing to the owning side, Project has the JoinColumn*/
    /* mappedBy = do not create another join column as the relationship already being mappedBy*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projectList;

    /* Referencing to the owning side, Roles has the JoinColumn*/
    /* mappedBy = do not create another join column as the relationship already being mappedBy*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Role> roles = new ArrayList<Role>();

    public User() {

    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationPassword() {
        return registrationPassword;
    }

    public void setRegistrationPassword(String registrationPassword) {
        this.registrationPassword = registrationPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean isAdmin() {
        return hasRole(Role.RoleName.ADMIN);
    }

    public boolean isUser() {
        return hasRole(Role.RoleName.USER);
    }

    public boolean hasRole(Role.RoleName roleName) {
        for(Role role : roles) {
            if(roleName.toString().equals(role.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
