package com.flzssolutionsgmbh.projecttimebookingapp.service;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import com.flzssolutionsgmbh.projecttimebookingapp.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**/
@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    private String variable;


    public void addProject(Project project){

        //add additional if statement to ensure that the project is not existing yet
        projectRepository.save(project);
    }

    public void deleteProject(Project project){
        projectRepository.delete(project);
    }

    public void updateProject(Project project, Long id){
        if(project.getName()==null){

        }
        projectRepository.findById(id);
    }

    public void updateProject(Project project, String name){
        projectRepository.findByName(project.getName());
    }


    //For dashboard
    public Long getAllProjectsNumber(){
        return projectRepository.countAllByActiveIsTrue();
    }


    //get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    //Saving the project
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    //Find project by id
    public Project findProject(Long id) {
        Optional<Project> p = projectRepository.findById(id);
        return p.get();
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



