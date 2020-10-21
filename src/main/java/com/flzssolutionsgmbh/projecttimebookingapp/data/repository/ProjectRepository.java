package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*tbd to other repos*/
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser(User user);
}
