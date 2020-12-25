package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectDailyTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectTotalTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.ProjectRepository;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.ProjectUserTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectUserTimeRepository projectUserTimeRepository;


    public void addProject(Project project){

        //add additional if statement to ensure that the project is not existing yet
        projectRepository.save(project);
    }

    public Project findProject(Long id) {
        Optional<Project> p = projectRepository.findById(id);
        return p.get();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Project project){
        projectRepository.delete(project);
    }

    //For dashboard
    public Long getAllProjectsNumber(){
        return projectRepository.countAllByActiveIsTrue();
    }


    public Long getAllProjectsTimeSpent() {
        return projectRepository.getAllByTimeSpentTotal();
    }


    public List<IProjectDailyTimeStatistics>getProjectTimeStatistics(){
        return projectRepository.getProjectTimeStatistics();
    }

    /*Pageable object will consist of PageRequest PageNo, PageSize, Sorting
     * By default, records are ordered in DESCENDING order. To choose ASCENDING order, use .ascending() method.
     *
     * A Page object has the number of total pages, number of the current page and well as whether current page
     * is first page or last page.
     * */
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Page<Project> getAllUserProjects(Pageable pageable, User user) {
        return projectRepository.findAllUserProjects(pageable, user.getId());
    }

    public List<IProjectTotalTimeStatistics> getProjectsTotalMinutesSpent(Set<Long> projectIds) {
        return projectUserTimeRepository.getProjectsTotalMinutesSpent(projectIds);
    }

    public Long getTotalProjects() {
        return projectRepository.count();
    }

    //Find all projects defined by User
    public List<Project> findUserProject(User user){
        return projectRepository.findByUser(user);
    }


    public void calculateTimeDifference(String startDate, String endDate){
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = dateFormat.parse(startDate);
            Date d2 = dateFormat.parse(endDate);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print(
                    "Difference "
                            + "between two dates is: ");

            System.out.println(
                    difference_In_Years
                            + " years, "
                            + difference_In_Days
                            + " days, "
                            + difference_In_Hours
                            + " hours, "
                            + difference_In_Minutes
                            + " minutes, "
                            + difference_In_Seconds
                            + " seconds");
        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }


}


