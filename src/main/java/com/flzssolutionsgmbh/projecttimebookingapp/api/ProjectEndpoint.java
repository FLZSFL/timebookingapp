package com.flzssolutionsgmbh.projecttimebookingapp.api;


import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectTotalTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.ProjectUserTime;
import com.flzssolutionsgmbh.projecttimebookingapp.service.ProjectService;
import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class ProjectEndpoint {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping(path = "/project-totals", produces = "application/json")
    public Map<String, Long> projectTotals() {

        Long timespent = projectService.getAllProjectsTimeSpent();
        Long totalProjects = projectService.getAllProjectsNumber();

        Map<String, Long> map = new HashMap<>(2);
        map.put("totalTime", timespent);
        map.put("totalProjects", totalProjects);
        return map;
    }



    @GetMapping(path = "/project-time-statistics", produces = "application/json")
    public List<IProjectTotalTimeStatistics> getProjectTimeStatistics() {
        return projectService.getProjectTimeStatistics();
    }

    @GetMapping(path = "/projects", produces = "application/json")
    public Page<Project> projects(@RequestParam int currentPage, @RequestParam int pageSize) {

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return projectService.getAllProjects(pageable);
    }


    @PostMapping(path = "/book-project", produces = "application/json")
    public Project bookProject(@RequestParam Long projectId, @RequestParam String startTime, @RequestParam String endTime) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDatetime = dateFormat.parse(startTime);
        Date endDatetime = dateFormat.parse(endTime);

        Project project = projectService.findProject(projectId);

        ProjectUserTime time = new ProjectUserTime();
        time.setProject(project);
        //time.setUser(user); TODO
        time.setStartTime(startDatetime);
        time.setEndTime(endDatetime);
        time.setEntered(new Date(System.currentTimeMillis()));
        project.addProjectUserTime(time);
        projectService.saveProject(project);

        return project;
    }


    @GetMapping(path = "/create-projects", produces = "application/json")
    public String createProjects() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startTime = dateFormat.parse("2020-11-10");
        Date endTime = dateFormat.parse("2020-12-10");

        Random rand = new Random();

        for(int i=0; i<100; i++) {
            Project p = new Project();
            p.setName("Project " + (i+1));
            p.setStartTime(startTime);
            p.setEndTime(endTime);
            p.setTimeSpent(0L);
            projectService.addProject(p);

            Date startDatetime = datetimeFormat.parse("2020-11-20 15:00:00");
            Date endDatetime = datetimeFormat.parse("2020-11-20 16:00:00");
            Date entered = datetimeFormat.parse("2020-11-24 17:00:00");

            for(int j=0; j<7; j++) {

                ProjectUserTime put = new ProjectUserTime();
                put.setProject(p);
                put.setStartTime(startDatetime);
                put.setEndTime(endDatetime);
                put.setEntered(entered);

                p.addProjectUserTime(put);

                int hour = rand.nextInt(18);
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDatetime);
                cal.add(Calendar.DATE, 1);
                cal.set(Calendar.HOUR, hour);
                startDatetime = cal.getTime();

                cal.setTime(endDatetime);
                cal.add(Calendar.DATE, 1);
                cal.set(Calendar.HOUR, hour + rand.nextInt(5));
                endDatetime = cal.getTime();

            }

            projectService.saveProject(p);
        }

        return "Done";
    }

    @GetMapping(path = "/create-project-times", produces = "application/json")
    public String createProjectTimes() throws ParseException {

        Project project = projectService.findProject(1L);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = dateFormat.parse("2020-11-24 15:00:00");
        Date endTime = dateFormat.parse("2020-11-24 16:00:00");
        Date entered = dateFormat.parse("2020-11-24 17:49:00");

        ProjectUserTime put = new ProjectUserTime();
        put.setProject(project);
        put.setStartTime(startTime);
        put.setEndTime(endTime);
        put.setEntered(entered);

        project.addProjectUserTime(put);

        projectService.saveProject(project);

        return "Done";
    }

    @GetMapping(path = "/project-times", produces = "application/json")
    public List<ProjectUserTime> projectTimes(@RequestParam Long projectId) {
        Project project = projectService.findProject(projectId);
        if(project != null) {
            return project.getProjectUserTimes();
        }

        return null;
    }




}
