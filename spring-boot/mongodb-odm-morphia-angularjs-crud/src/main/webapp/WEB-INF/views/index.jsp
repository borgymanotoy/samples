<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html ng-app="consumeRestApiApp">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Angular + REST API - CRUD</title>

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

        <style media="screen">
            tr>td:nth-of-type(5), tr>td:nth-of-type(6){
               width: 30px;
            }

            div.navbar-header > a > p.header-title, div > a.nav-item {
                font-size: 22px;
                font-weight: bold;
                color: #eee;
                padding: 3px;
            }

            .adjustTopPosition {
                top: 10px;
            }



            .busi-btn{    border-radius: 0px;	color: #003333 !important;	font-size: 18px; font-weight: 400; }
            .busi-btn:hover{	border-radius: 0px;	color: #ff3c2d !important;	font-size: 18px; font-weight: 400; }
            .busi-btn-active,.busi-btn-active:hover{	border-radius: 0px;	color: #ff3c2d !important;	font-size: 18px; font-weight: 400; }


            .hom_nav_btn_red{  /* sign-up button  */
                border-radius: 0px;
                background-color:#ff3333; /* red color */
                border:0px !important;
                font-size:18px;
                font-weight: 400;
                padding: 7px 30px;
                margin: 0 -25px;
            }
            .hom_nav_btn{
                border-radius: 0px;
                color:#003333 !important; /* blue color */
                font-size:18px;
                font-weight: 400;
                padding: 7px 30px;
            }
            .navbar-centerr{
                float:none;
                margin: 0 auto;
                display: table;
                table-layout: fixed;
            }

            .business-logo-align {
                float: left;
                font-size: 18px;
                line-height: 20px;
                height: 50px;
                padding-left: 5px;
            }

            img.logo { width: 90px; }
            #searchContainer { width: 500px; }
            button.navbar-toggle {
                float: left;
                margin-left: 10px;
                margin-right: 0;
            }

            @media screen and (max-width : 480px) {
                img.logo { width:48px; }
                #searchContainer { width: 300px; }
            }

        </style>
    </head>
    <body ng-controller="consumeRestApiController">

<%--
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="navigation-panel container">
                <div class="row">
                    <div class="navbar-header">
                        <a class="navbar-brand text-capitalize title" href="#"><p class="header-title">Contacts</p></a>
                    </div>
                    <div id="navbar" class="collapse navbar-collapse" title="Search Contact">
                        <div class="input-group custom-search-form" style="width:300px;">
                            <input type="text" class="form-control adjustTopPosition" placeholder="Search" ng-model="searchKey">
                            <span class="input-group-btn adjustTopPosition">
                                <button class="btn btn-default btn-search" type="button">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                        <div class="collapse navbar-collapse navbar-nav pull-right" id="navbarResponsive">
                            <a href="/logout" class="nav-item">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
--%>

        <nav class="navbar navbar-default " role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="business-logo-align col-md-10" >
                    <a href="javascript:void(0);" onclick="windows.location='/home'"><img class="img-responsive logo" src="images/spring-logo.png" alt="logo"/></a>
                </div>
            </div>

            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="col-md-6 col-xs-6 text-center"> <a href="#"><button type="button" class=" btn btn-default hom_nav_btn ">Log in</button></a></li>
                    <li class="col-md-6 col-xs-6 text-center"><a href="#"><button type="button" class="btn btn-danger hom_nav_btn_red">Sign Up</button></a></li>
                </ul>

            </div>
        </nav>

        <div class="container">
            <div id="searchContainer" class="input-group custom-search-form">
                <input type="text" id="txtUserSearch" class="form-control adjustTopPosition" placeholder="User search" ng-model="searchKey">
                <span class="input-group-btn adjustTopPosition">
                    <button class="btn btn-default btn-search" type="button">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
            <hr>
            <button type="button" class="btn btn-info btn-lg pull-right" data-toggle="modal" data-target="#addUser">Add New</button>
            <h2>Manage Users</h2>
            <span class="clearfix"></span>
            <hr>

            <div class="alert alert-success text-center" ng-if="message">
                <a href="#" class="close" aria-label="close" ng-click="clearMessage()">&times;</a>
                <strong>{{message}}</strong>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Contact No.</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="user in users">
                        <td><b>{{user.username}}</b></td>
                        <td>{{user.firstName + " " + user.lastName}}</td>
                        <td>{{user.email}}</td>
                        <td>{{user.contactNo}}</td>
                        <td>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#editUser" ng-click="displayUserDetails(user.username)">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteUser" ng-click="displayUserDetails(user.username)">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Modal for Add User -->
        <div class="modal fade" id="addUser" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">User Registration</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="txtNewUsername">Username</label>
                            <input type="text" class="form-control" id="txtNewUsername" placeholder="Username" name="username"  ng-model="newUser.username">
                        </div>
                        <div class="form-group">
                            <label for="txtNewFirtstName">First Name</label>
                            <input type="text" class="form-control" id="txtNewFirtstName" placeholder="First Name" ng-model="newUser.firstName">
                        </div>
                        <div class="form-group">
                            <label for="txtNewLastName">Last Name</label>
                            <input type="text" class="form-control" id="txtNewLastName" placeholder="Last Name" name="lastName" ng-model="newUser.lastName">
                        </div>
                        <div class="form-group">
                            <label for="txtNewEmail">Email Address</label>
                            <input type="email" class="form-control" id="txtNewEmail" placeholder="Email Address" name="email" ng-model="newUser.email">
                        </div>
                        <div class="form-group">
                            <label for="txtNewContactNo">Contact Number</label>
                            <input type="tel" class="form-control" id="txtNewContactNo" placeholder="Contact Number" name="contactNo" ng-model="newUser.contactNo">
                        </div>
                        <div class="form-group">
                            <label for="txtPassword">Password:</label>
                            <input type="password" class="form-control" id="txtPassword" placeholder="Password" name="password" ng-model="newUser.password">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" data-dismiss="modal" ng-click="saveNewUser()">Save Details</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Add User -->

        <!-- Modal for Edit User -->
        <div class="modal modal-edit-user fade" id="editUser" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">User Details</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="txtUpdateUsername">Username</label>
                            <input type="text" class="form-control" id="txtUpdateUsername" placeholder="Enter Username" name="username" ng-model="selectedUser.username" readonly>
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateFirtstName">First Name</label>
                            <input type="text" class="form-control" id="txtUpdateFirtstName" placeholder="Enter First Name" name="firstName" ng-model="selectedUser.firstName">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateLastName">Last Name</label>
                            <input type="text" class="form-control" id="txtUpdateLastName" placeholder="Enter Last Name" name="lastName" ng-model="selectedUser.lastName">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateEmail">Email Address</label>
                            <input type="email" class="form-control" id="txtUpdateEmail" placeholder="Email Address" name="email" ng-model="selectedUser.email">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateContactNo">Contact Number</label>
                            <input type="tel" class="form-control" id="txtUpdateContactNo" placeholder="Contact Number" name="contactNo" ng-model="selectedUser.contactNo">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdatePassword">Password:</label>
                            <input type="password" class="form-control" id="txtUpdatePassword" placeholder="Enter password" name="password" ng-model="selectedUser.password">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" ng-click="updateUser()" data-dismiss="modal">Update Details</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Edit User -->

        <!-- Modal for Delete User -->
        <div class="modal fade" id="deleteUser" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Remove User</h4>
                    </div>
                    <div class="modal-body">
                        <p><strong>Are you sure you want to delete {{selectedUser.firstName + " " + selectedUser.lastName}}?</strong></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="removeUser(selectedUser.username)">Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Delete User -->

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <!-- <script type="text/javascript" src="../js/app.js"></script> -->
        <script type="text/javascript" src="js/appConsumeRestApi.js"></script>
    </body>
</html>
