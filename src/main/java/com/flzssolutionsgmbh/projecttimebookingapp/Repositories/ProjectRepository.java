package com.flzssolutionsgmbh.projecttimebookingapp.Repositories;

import com.flzssolutionsgmbh.projecttimebookingapp.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
