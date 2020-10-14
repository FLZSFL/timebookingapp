package com.flzssolutionsgmbh.projecttimebookingapp.Repositories;

import com.flzssolutionsgmbh.projecttimebookingapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
