package com.flzssolutionsgmbh.projecttimebookingapp.api;


import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.*;
import com.flzssolutionsgmbh.projecttimebookingapp.service.ProjectService;
import com.flzssolutionsgmbh.projecttimebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ProjectEndpoint {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/create-project", produces = "application/json")
    public Project createProject(HttpServletRequest request, @RequestBody Project newProject) {
        User user = (User)userService.loadUserByUsername(request.getRemoteUser());
        newProject.setUser(user);
        newProject.setActive(true);

        projectService.addProject(newProject);

        return newProject;
    }

    @GetMapping(path = "/project-totals", produces = "application/json")
    public Map<String, Long> projectTotals() {

        Long timespent = projectService.getAllProjectsTimeSpent();
        Long totalProjects = projectService.getAllProjectsNumber();

        if(timespent == null) {
            timespent = 0L;
        }

        Map<String, Long> map = new HashMap<>(2);
        map.put("totalTime", timespent);
        map.put("totalProjects", totalProjects);
        return map;
    }



    @GetMapping(path = "/project-time-statistics", produces = "application/json")
    public List<IProjectDailyTimeStatistics> getProjectTimeStatistics() {
        return projectService.getProjectTimeStatistics();
    }

    @GetMapping(path = "/projects", produces = "application/json")
    public Page<Project> projects(HttpServletRequest request, @RequestParam int currentPage, @RequestParam int pageSize) {

        Pageable pageable = PageRequest.of(currentPage, pageSize);

        User user = (User)userService.loadUserByUsername(request.getRemoteUser());

        /*Determining if the user is admin = should see all projects
        * otherwise only the user's projects will be visible*/
        Page<Project> projectsPage;
        if(user.isAdmin()) {
            projectsPage = projectService.getAllProjects(pageable);
        } else {
            projectsPage = projectService.getAllUserProjects(pageable, user);
        }

        Map<Long, Project> projectsById = new HashMap<Long, Project>();

        /*Giving ids to the projects*/
        List<Project> projects = projectsPage.getContent();
        for(Project project : projects) {
            projectsById.put(project.getId(), project);
        }

        System.out.println(projectsById);


        List<IProjectTotalTimeStatistics> projectTimes = projectService.getProjectsTotalMinutesSpent(projectsById.keySet());

        for(IProjectTotalTimeStatistics projectTime : projectTimes) {
            System.out.println("Processing project time for ID " + projectTime.getProjectId() + " total time is " + projectTime.getTotalSpentMinutes());
            projectsById.get(projectTime.getProjectId()).setTotalSpentMinutes(projectTime.getTotalSpentMinutes());
        }

        return projectsPage;
    }

    @PostMapping(path = "/book-project", produces = "application/json")
    public Project bookProject(HttpServletRequest request, @RequestParam Long projectId, @RequestParam String startTime, @RequestParam String endTime) throws ParseException {

        User user = (User)userService.loadUserByUsername(request.getRemoteUser());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date startDatetime = dateFormat.parse(startTime);
        Date endDatetime = dateFormat.parse(endTime);

        Project project = projectService.findProject(projectId);

        ProjectUserTime time = new ProjectUserTime();
        time.setProject(project);
        time.setUser(user);
        time.setStartTime(startDatetime);
        time.setEndTime(endDatetime);
        time.setEntered(new Date(System.currentTimeMillis()));
        project.addProjectUserTime(time);
        projectService.saveProject(project);

        return project;
    }


    @GetMapping(path = "/create-userAdmin", produces = "application/json")
    public String createProjects() throws ParseException {

        User user = new User();
        user.setEmail("admin@test.com");
        user.setRegistrationPassword("test");
        user.setFirstName("Frenk");
        user.setLastName("Locmelis");
        user.setAddress("Street 22");
        user.setCity("MyCity");
        user.setCountry("MyCountry");

        Role role = new Role(Role.RoleName.ADMIN.toString());
        role.setUser(user);
        user.addRole(role);
        userService.createUser(user);

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

