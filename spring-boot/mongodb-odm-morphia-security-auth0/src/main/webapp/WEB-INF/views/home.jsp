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
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="glyphicon glyphicon-contact"></i>&nbsp;
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
            <button type="button" class="btn btn-primary btn-lg pull-right" data-toggle="modal" data-target="#addContact">Add New</button>
            <h2>Manage Contacts</h2>
            <span class="clearfix"></span>
            <hr>

            <div class="alert alert-success text-center" ng-if="message">
                <a href="#" class="close" aria-label="close" ng-click="clearMessage()">&times;</a>
                <strong>{{message}}</strong>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Contact No.</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="contact in contacts">
                        <td>{{contact.firstName + " " + contact.lastName}}</td>
                        <td>{{contact.email}}</td>
                        <td>{{contact.contactNo}}</td>
                        <td>
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editContact" ng-click="displayContactDetails(contact.id)">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteContact" ng-click="displayContactDetails(contact.id)">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Modal for Add Contact -->
        <div class="modal fade" id="addContact" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Contact Registration</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="txtNewFirtstName">First Name</label>
                            <input type="text" class="form-control" id="txtNewFirtstName" placeholder="First Name" ng-model="newContact.firstName">
                        </div>
                        <div class="form-group">
                            <label for="txtNewLastName">Last Name</label>
                            <input type="text" class="form-control" id="txtNewLastName" placeholder="Last Name" name="lastName" ng-model="newContact.lastName">
                        </div>
                        <div class="form-group">
                            <label for="txtNewEmail">Email Address</label>
                            <input type="email" class="form-control" id="txtNewEmail" placeholder="Email Address" name="email" ng-model="newContact.email">
                        </div>
                        <div class="form-group">
                            <label for="txtNewContactNo">Contact Number</label>
                            <input type="tel" class="form-control" id="txtNewContactNo" placeholder="Contact Number" name="contactNo" ng-model="newContact.contactNo">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" data-dismiss="modal" ng-click="saveNewContact()">Save Details</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Add Contact -->

        <!-- Modal for Edit Contact -->
        <div class="modal modal-edit-contact fade" id="editContact" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Contact Details</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="txtUpdateID">ID</label>
                            <input type="text" class="form-control" id="txtUpdateID" placeholder="Contact ID" name="id" ng-model="selectedContact.id" readonly>
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateFirtstName">First Name</label>
                            <input type="text" class="form-control" id="txtUpdateFirtstName" placeholder="Enter First Name" name="firstName" ng-model="selectedContact.firstName">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateLastName">Last Name</label>
                            <input type="text" class="form-control" id="txtUpdateLastName" placeholder="Enter Last Name" name="lastName" ng-model="selectedContact.lastName">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateEmail">Email Address</label>
                            <input type="email" class="form-control" id="txtUpdateEmail" placeholder="Email Address" name="email" ng-model="selectedContact.email">
                        </div>
                        <div class="form-group">
                            <label for="txtUpdateContactNo">Contact Number</label>
                            <input type="tel" class="form-control" id="txtUpdateContactNo" placeholder="Contact Number" name="contactNo" ng-model="selectedContact.contactNo">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" ng-click="updateContact()" data-dismiss="modal">Update Details</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Edit Contact -->

        <!-- Modal for Delete Contact -->
        <div class="modal fade" id="deleteContact" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Remove User</h4>
                    </div>
                    <div class="modal-body">
                        <p><strong>Are you sure you want to delete {{selectedContact.firstName + " " + selectedContact.lastName}}?</strong></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="removeContact(selectedContact.id)">Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Modal For Delete Contact -->

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/appConsumeRestApi.js"></script>

        <%--

        --%>
    </body>
</html>
