package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Role {

    public enum RoleName {
        ADMIN,
        USER,
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /*JoinColumn = Role Entity will have a foreign key name user_id referring to primary
    * entity called id in the entity Role*/

    /*@OnDelete in hibernate is used when there are joined sub class.
     * @OnDelete decides whether deleting an entry from database will delete
     * the rows represented by joined sub class or not*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Role() {
    }


    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

