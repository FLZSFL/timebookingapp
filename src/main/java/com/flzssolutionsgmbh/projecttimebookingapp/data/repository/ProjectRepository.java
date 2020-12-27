package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectDailyTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/*extends Paging and Sorting Repository helps to sort and paginate the content in the
* backend so the content can be passed to front-end properly*/
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    List<Project> findByUser(User user);
    List<Project> findByName(String name);
    List<Project> findById(Project project);

    /*For Dashboard, counts all projects active
     * Will be used for the later phase to distinct between active
     * not active projects
     * */
    Long countAllByActiveIsTrue();


    /*When using Pageable, Pageable Object is created with certain properties such as
    * page size, current page number, sorting*/
    @Query(value = "SELECT * FROM project "
            + "WHERE user_id = :user_id", nativeQuery = true)
    Page<Project> findAllUserProjects(Pageable pageable, @Param("user_id") Long userId);


    @Query(value = "SELECT SUM(EXTRACT(EPOCH FROM end_time - start_time) / 60) FROM PROJECT_USER_TIME", nativeQuery = true)
    Long getAllByTimeSpentTotal();

    /*H2 database*/
    @Query(value = "SELECT FORMATDATETIME(t.START_TIME, 'yyyy-MM-dd') AS day, SUM(EXTRACT(EPOCH FROM end_time - start_time) / 60) AS totalMinutes "
            + "FROM PROJECT_USER_TIME t "
            + "GROUP BY FORMATDATETIME(t.START_TIME, 'yyyy-MM-dd')", nativeQuery = true)
    List<IProjectDailyTimeStatistics> getProjectTimeStatistics();
    /*End of H2 database*/

    /*Postgres database*/
//    @Query(value = "SELECT DATE(t.start_time) AS day, SUM(EXTRACT(EPOCH FROM t.end_time - t.start_time) / 60) AS totalMinutes "
//    		+ "FROM project_user_time t "
//    		+ "GROUP BY DATE(t.start_time) "
//    		+ "ORDER BY day", nativeQuery = true)
//    List<IProjectDailyTimeStatistics> getProjectTimeStatistics();
    /*End of Postgres database*/

}

