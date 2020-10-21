package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Project {


    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private Double hoursSpent;
    private Double minutesSpent;



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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Double hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public Double getMinutesSpent() {
        return minutesSpent;
    }

    public void setMinutesSpent(Double minutesSpent) {
        this.minutesSpent = minutesSpent;
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
}
