package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean active;


    //Formatting the Date
    @Temporal(TemporalType.DATE)
    private Date startTime;

    @Temporal( TemporalType.DATE)
    private Date endTime;

    //time spent for the project on particular day
    @Temporal(TemporalType.DATE)
    private Date timeSpent;

    //total spent time for project
    @Temporal(TemporalType.DATE)
    private Date timeSpentTotal;






    //description of what have been done on "time spent"
    private String activityDescription;

    @ManyToOne
    private User user;


    public Project() {
        active = true;
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

    public Date getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Date timeSpent) {
        this.timeSpent = timeSpent;
    }
}
