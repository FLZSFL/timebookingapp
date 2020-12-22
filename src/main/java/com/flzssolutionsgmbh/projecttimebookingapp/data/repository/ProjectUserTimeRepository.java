package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectTotalTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.ProjectUserTime;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProjectUserTimeRepository extends JpaRepository<ProjectUserTime, Long> {

    List<ProjectUserTime> findByUser(User user);
    List<ProjectUserTime> findByProject(Project project);

    @Query(value = "SELECT project_id AS projectId, SUM(EXTRACT(EPOCH FROM end_time - start_time) / 60) AS totalSpentMinutes "
            + "FROM project_user_time WHERE project_id IN (:project_ids) "
            + "GROUP BY project_id", nativeQuery = true)
    List<IProjectTotalTimeStatistics> getProjectsTotalMinutesSpent(@Param("project_ids") Set<Long> projectIds);

}
