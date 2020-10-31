package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //Formatting the Date
    @Temporal(TemporalType.DATE)
    private Date date;
    private Double hoursSpent;




    private String activityDescription;

    @ManyToOne
    private User user;


    public Project() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Double hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
