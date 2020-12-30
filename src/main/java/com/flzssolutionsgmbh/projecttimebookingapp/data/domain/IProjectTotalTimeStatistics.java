package com.flzssolutionsgmbh.projecttimebookingapp.data.domain;


/*For getting total time for projects*/
public interface IProjectTotalTimeStatistics {

    public Long getProjectId();

    public Long getTotalSpentMinutes();

}
