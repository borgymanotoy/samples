<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html ng-app="consumeRestApiApp">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Angular + REST API - CRUD</title>

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link href="css/phonebook.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">

        <style media="screen">
            tr>td:nth-of-type(5), tr>td:nth-of-type(6){
               width: 30px;
            }

            .btn-success {
                /* color: #fff; */
                background-color: #5cb85c;
                border-color: #4cae4c;
            }

            div>a.nav-item {
                font-size: 24px;
                color: #ffffff;
                text-decoration: none;
            }
        </style>
    </head>
    <body ng-controller="consumeRestApiController">

<%--        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="navigation-panel container">
                <div class="row">
                    <div class="navbar-header">
                        <a class="navbar-brand text-capitalize" href="#"><p class="header-title">Contacts</p></a>
                    </div>
                    <div id="navbar" class="collapse navbar-collapse" title="Search Contact">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search" ng-model="searchKey">
                            <span class="input-group-btn">
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
        </nav>--%>


        <header>
            <nav class="container navbar main-nav navbar-inverse navbar-fixed-top navbar-static-top marginBottom-0" role="navigation">
                <div class="collapse navbar-collapse" id="navbar-collapse-1">
                    <div class="col-md-3 col-sm-12 col-xs-12 search-container">
                        <div class="input-group col-md-10 col-sm-12 col-xs-12">
                            <input type="text" id="txtSearchItinerary" class="form-control search-reloc" title="Enter Record Locator to search" placeholder="Search" maxlength="18" >
                            <i class="search-icon glyphicon glyphicon-search"></i>
                        </div>
                    </div>
                    <ul class="nav navbar-nav">

                        <li class="dropdown" id="menuLogin">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-click="dropdown" data-delay="0" data-close-others="false" href="#">
                                <i class="fa fa-user" ></i>&nbsp;
                                <span>Bilat Bombay</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="glyphicon glyphicon-user"></i>&nbsp;
                                        <span>My Profile</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="glyphicon glyphicon-lock"></i>&nbsp;
                                        <span>Change Password</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="logout()">
                                        <i class="glyphicon glyphicon-log-out"></i>&nbsp;
                                        <span>Logout</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>




        <div class="container">
            <hr>
            <button type="button" class="btn btn-primary btn-lg pull-right" data-toggle="modal" data-target="#addUser">Add New</button>
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
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editUser" ng-click="displayUserDetails(user.username)">
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
