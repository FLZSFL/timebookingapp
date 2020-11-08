package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/*tbd to other repos*/
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser(User user);
    List<Project> findByName(String name);
    List<Project> findById(Project project);

    //For Dashboard, counts all projects active
    Long countAllByActiveIsTrue();

    //for Dashboard
    @Query(value = "SELECT SUM(timeSpentTotal) FROM Project", nativeQuery = true)
    Date getAllByTimeSpentTotal();



}
