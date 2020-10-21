package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndIdNot(String email, Long agendId);
}
