<!DOCTYPE html>
<html lang="en" ng-app="authApp">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contacts - Login</title>

        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">
    </head>
    <body ng-controller="authController">

        <div class="login-page">
            <div class="form">
                <form class="login-form">
                    <input type="text" placeholder="username"/>
                    <input type="password" placeholder="password"/>
                    <button>login</button>
                    <p class="message">Not registered? <a href="#">Create an account</a></p>
                </form>
            </div>
        </div>

        <!--jQuery and AngularJS-->
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/angular.min.js"></script>

    </body>
</html>