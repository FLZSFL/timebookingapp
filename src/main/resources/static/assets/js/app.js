
var serviceEndpointURL = window.location.protocol + "//" + window.location.host;


function getProjects(currentPage, pageSize, onSuccess, onError) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/api/projects?currentPage=" + currentPage + "&pageSize=" + pageSize,
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}


function bookProject(projectId, startTime, endTime, onSuccess, onError) {
    $.ajax({
        type: "POST",
        url: serviceEndpointURL + "/api/book-project",
        data: 'projectId=' + projectId + '&startTime=' + startTime + '&endTime=' + endTime,
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}


//This function returns total time and total number of projects used for dashboard
function getProjectTotals(onSuccess, onError) {
    $.ajax({
        type: "GET",
        url: serviceEndpointURL + "/api/project-totals",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}


function getProjectTimeStatistics(onSuccess, onError) {
    $.ajax({
        type: "GET",
        url: serviceEndpointURL + "/api/project-time-statistics",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}
