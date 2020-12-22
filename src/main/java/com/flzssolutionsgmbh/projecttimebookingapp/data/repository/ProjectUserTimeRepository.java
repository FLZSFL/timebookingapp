package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.ProjectUserTime;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectUserTimeRepository extends JpaRepository<ProjectUserTime, Long> {

    List<ProjectUserTime> findByUser(User user);
    List<ProjectUserTime> findByProject(Project project);

}
