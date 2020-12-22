package com.flzssolutionsgmbh.projecttimebookingapp.data.repository;

import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.IProjectTimeStatistics;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.Project;
import com.flzssolutionsgmbh.projecttimebookingapp.data.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
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




    /*Calculating Time difference + Sum it, TIMESTAMPDIFF calculates in units, start - end. In our
    * case it calculates minutes from starttime and endtime from Projectusertime*/
    @Query(value = "SELECT SUM(TIMESTAMPDIFF(MINUTE, START_TIME, END_TIME)) FROM PROJECT_USER_TIME", nativeQuery = true)
    Long getAllByTimeSpentTotal();


    @Query(value = "SELECT FORMATDATETIME(t.START_TIME, 'yyyy-MM-dd') AS day, SUM(TIMESTAMPDIFF(MINUTE, START_TIME, END_TIME)) AS totalHours "
            + "FROM PROJECT_USER_TIME t "
            + "GROUP BY FORMATDATETIME(t.START_TIME, 'yyyy-MM-dd')", nativeQuery = true)
    List<IProjectTimeStatistics> getProjectTimeStatistics();




}
