
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

function createProject(project, onSuccess, onError) {
    console.log('Creating project', project);

    $.ajax({
        type: "POST",
        url: serviceEndpointURL + "/api/create-project",
        data: JSON.stringify(project),
        contentType: "application/json; charset=utf-8",
    	dataType: "json",
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

function registerUser(user, onSuccess, onError) {

	console.log('Registering user', user);

    $.ajax({
        type: "POST",
        url: serviceEndpointURL + "/api/register-user",
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
    	dataType: "json",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function loadUser(onSuccess, onError) {
	$.ajax({
        type: "GET",
        url: serviceEndpointURL + "/api/user",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function updateUser(user, onSuccess, onError) {
	$.ajax({
        type: "PUT",
        url: serviceEndpointURL + "/api/user",
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
    	dataType: "json",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function updateUserInfo(user, onSuccess, onError) {
	$.ajax({
        type: "PUT",
        url: serviceEndpointURL + "/api/user-info",
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
    	dataType: "json",
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function changePassword(password, onSuccess, onError) {
	$.ajax({
        type: "POST",
        url: serviceEndpointURL + "/api/change-password",
        data: "password=" + password,
        success: function (data, textStatus, response) {
            onSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            onError(errorThrown);
        }
    });
}

function showSuccess(message) {		
	showMessage('<div class="alert alert-success text-center" role="alert">' +
				message +
			'</div>', true);
}

function showError(message) {
	if(!message || message == '') {
		message = 'The system error has happened!';
	}

	showMessage('<div class="alert alert-danger alert-dismissible text-center" role="alert">' +
				message +
				'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
    				'<span aria-hidden="true">&times;</span>' +
  				'</button>' +				
			'</div>');
}

function showMessage(html, autoHide) {
	var container = $(".alerts-container");
	container.html(html);
	container.slideDown(400);
	
	if(autoHide) {
		window.setTimeout(function() {
			container.slideUp(400);
			container.html('');
		}, 2500);
	}	
}

function formatTimeInHours(minutes) {
	var hours = Math.floor(minutes / 60);
	minutes = minutes - hours * 60;
	
	return hours + "h:" + minutes + "m";
}

function initCsrf(onSuccess, onError) {
    $.ajax({
        type: "GET",
        url: "/csrf",
        success: function (data, textStatus, response) {
            var header = response.getResponseHeader('x-csrf-header');
            var param = response.getResponseHeader('x-csrf-param');
            var token = response.getResponseHeader('x-csrf-token');

            $.ajaxSetup({
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                }
            });

            onSuccess && onSuccess({
                header: header,
                param: param,
                token: token,
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR, textStatus, errorThrown);
            onError && onError(errorThrown);
        }
    });
}

/*Function is called automatically*/
initCsrf();


