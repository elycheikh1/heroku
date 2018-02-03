function loadMoviesList() {
    $("#mainDiv").load("html/MoviesList.html",function () {
        fillTheTable();

        $("#mainDiv").delegate('#delete', 'click', function () {
            deleteMovie($(this));
        });

        $("#mainDiv").delegate('#update', 'click', function () {
            var id = $(this).closest("tr").data("id");
            $("#mainDiv").load("html/CreateMovie.html",function () {
                fillTheForm(id)
                $("#submitM").click(updateMovie);
            });
        });
    });
}

function createMovie(){
    var Obj = {
        title: $("#titleM").val(),
        director: $("#dir").val()
    }

    $.ajax({
        url: "/movies",
        method: 'post',
        dataType: 'json',
        Async :false,
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json'
        },
        data: JSON.stringify(Obj),
        success: function (data) {
        }
    });

    loadMoviesList();
}

function updateMovie(){
    var Obj = {
        title: $("#titleM").val(),
        director: $("#dir").val()
    }

    $.ajax({
        url: "/movies/"+$("#theID").val(),
        method: 'put',
        dataType: 'json',
        Async :false,
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json'
        },
        data: JSON.stringify(Obj),
        success: function (data) {
        }
    });

    loadMoviesList();
}

function fillTheTable() {
    $.ajax({
        url: "/movies",
        success: function(data){
            var trs = ""
            $.each(data, function (index, value) {
               trs += "<tr data-id='"+value['id']+"'><td>"+value['id']+"</td> \
                            <td>"+value['title']+"</td> \
                            <td>"+value['director']+"</td>\
                            <td><button class='btn' id='update'>Update</button> | <button class='btn' id='delete'>Remove</button></td></tr>";
            });

            $("#moviesTable tbody").html(trs);
        },
    
    error : function (jqXHR, textStatus, errorThrown) {
    	debugger
		if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
			var preLoginInfo = JSON.stringify({method: 'GET', url: '/'});
			$.cookie('restsecurity.pre.login.request', preLoginInfo);
			window.location = '/login.html';

		} else {
			alert('Houston, we have a problem...');
		}
	}
    
    });
}

function deleteMovie(_this) {
    var id =_this.closest("tr").data("id");

    $.ajax({
        url: "/movies/"+id,
        method: 'delete',
        success: function (data) {
            _this.closest("tr").remove();
        }
    })
}

function fillTheForm(id){
    $.ajax({
        url: "/movies/"+id,
        success: function(data){
            $("#titleM").val(data["title"]);
            $("#dir").val(data["director"]);
            $("#theID").val(data["id"])
        }
    });
}

