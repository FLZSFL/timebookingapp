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
    private Date startTime;
    @Temporal( TemporalType.DATE)
    private Date endTime;







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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
