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

    //For dashboard (admin)
    public Long getAllProjectsNumber(){
        return projectRepository.countAllByActiveIsTrue();
    }

    //For dashboard
    public Long getAllProjectsNumberById(User user){
         List<Project> projects = projectRepository.findByUser(user);
         Long projectSize = (long) projects.size();

        return projectSize;
    }


    public Long getAllProjectsTimeSpent() {
        return projectRepository.getAllByTimeSpentTotal();
    }


    public Long getAllProjectsTimeSpentById(User user){
        return projectRepository.getAllByTimeSpentTotalById(user.getId());
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




}


