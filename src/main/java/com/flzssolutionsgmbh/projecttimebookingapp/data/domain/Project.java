package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Project {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean active;
    //Temporal data can have Date, Time or TimeStamp
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date startTime;
    //Temporal data can have Date, Time or TimeStamp
    @Temporal( TemporalType.DATE)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date endTime;
    private Long totalSpentMinutes;

    @ManyToOne
    private User user;


    /* JsonIgnore not used for serialization or deserialization
    (it doesn't go out of API). The value will be shown as null on the front-end */
    /* mappedBy = do not create another join column as the relationship already being mappedBy */
    /* LinkedList more convenient since there will be always added new entries addLast / getLast are methods which are more
    *  efficient than ArrayList */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    @JsonIgnore
    private List<ProjectUserTime> projectUserTimes = new LinkedList<ProjectUserTime>();


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getTotalSpentMinutes() {
        return totalSpentMinutes;
    }

    public void setTotalSpentMinutes(Long totalSpentMinutes) {
        this.totalSpentMinutes = totalSpentMinutes;
    }


    public void addProjectUserTime(ProjectUserTime time) {
        projectUserTimes.add(time);
    }

    public List<ProjectUserTime> getProjectUserTimes() {
        return projectUserTimes;
    }

}
