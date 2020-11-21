
var serviceEndpointURL = window.location.protocol + "//" + window.location.host;

function getProjects(onSuccess, onError) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/api/projects",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function bookProject(projectId, hours, onSuccess, onError) {
    $.ajax({
        type: "POST",
        url: serviceEndpointURL + "/api/book-project",
        data: 'projectId=' + projectId + '&hours=' + hours,
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}