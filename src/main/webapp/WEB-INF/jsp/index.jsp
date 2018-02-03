<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="initial-scale=1">
    <title>Movies</title>

    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" />

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../js/script.js" type="text/javascript"></script>
</head>
<body>

<div class="container mainDiv">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center mb-4">Welcome To Movies</h2>
            <div class="row">
                <div class="col-md-6 mx-auto">
                    <span class="anchor" id="formLogin"></span>

                    <!-- form card login -->
                    <div class="card rounded-0">
                        <div class="card-header">
                            <h3 class="mb-0">Login</h3>
                        </div>
                        <div class="card-body p-5">
                            <form action="" class="form" role="form" autocomplete="off" id="formLogin">
                                <div class="form-group">
                                    <label for="uname1">Username</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="uname1" id="userName" required="">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="pwd" required="" autocomplete="new-password">
                                </div>

                                <button type="button" id="login_js" class="btn btn-success btn-lg float-right">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function(){
        $("#login_js").click(function(){

            event.preventDefault();
            var data = 'username=' + $('#userName').val() + '&password=' + $('#pwd').val();
            $.ajax({
                data: data,
                timeout: 1000,
                type: 'POST',
                url: '/login'

            }).done(function(data, textStatus, jqXHR) {
               debugger
                        $(".mainDiv").load("/html/main.html",function(){
                            $("#jsCreateM").click(function () {
                                $("#mainDiv").load("html/CreateMovie.html",function () {
                                    $("#submitM").click(createMovie);
                                });
                            })

                            $("#jsListM").click(function () {
                                loadMoviesList();
                            })
                       
                })
            }).fail(function(jqXHR, textStatus, errorThrown) {

                alert('Booh! Wrong credentials, try again!');
            });

        });

    });
</script>

</body>
</html>

