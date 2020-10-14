package com.flzssolutionsgmbh.projecttimebookingapp.Repositories;

import com.flzssolutionsgmbh.projecttimebookingapp.Entities.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {




}
