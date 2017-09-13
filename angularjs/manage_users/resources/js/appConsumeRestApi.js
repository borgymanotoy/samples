angular
    .module("consumeRestApiApp", []);

angular
    .module("consumeRestApiApp")
    .controller("consumeRestApiController",
        ['$scope', '$http', 
            function($scope, $http) {


                $scope.users = [];
                $scope.populateDate = function(){
                    for(var i=1; i <= 200; i++){
                        var obj = new Object();
                        obj.username = "demo-user" + i;
                        obj.firstName = "Demo ";
                        obj.lastName = "User " + i;
                        obj.email = "demouser" + i + "@gmail.com";
                        obj.contactNo = "123-456" + i;

                        $scope.users.push(obj);
                    }

                    $scope.totalItems = $scope.users.length;
                    console.log('x: ' + $scope.totalItems.length);
                };

                // for message alert
                $scope.message = "";

                // for searching users (REST)
                $scope.searchKey = "";

                // Sorting column
                $scope.sortColumn = "users";
                $scope.reverseSort = false;
                $scope.sortData = function(column){
                    $scope.reverseSort = ($scope.sortColumn == column) ? !$scope.reverseSort : false;
                    $scope.sortColumn = column;
                };

                // Arrow Up and Down for sortColumn
                $scope.getSortClass = function(column){
                    if($scope.sortColumn == column){
                        return $scope.reverseSort ? "glyphicon glyphicon-arrow-down" : "glyphicon glyphicon-arrow-up"
                    }
                    return '';
                };

                $scope.clearMessage = function(){
                    $scope.message = "";
                };

                $scope.listUsers = function () {
                    $http.get('http://localhost:8080/api/listUsers')
                        .then(
                            function successCallback(obj){
                                $scope.users = obj.data;
                                if(obj.data)
                                    $scope.totalItems = obj.data.length;

                                console.log("length: " + $scope.totalItems);
                            },
                            function errorCallback(msg){
                                //console.log(obj);
                                $scope.message = msg;
                            }
                        );
                };

                $scope.searchEvent = function(event) {
                    var key = typeof event.which === "undefined" ? event.keyCode : event.which;
                    if (key === 13){
                        $http.get('http://localhost:8080/api/searchUsers?searchKey=' + $scope.searchKey + '&t=' + Date.now())
                            .then(
                                function successCallback(obj){
                                    $scope.users = obj.data;
                                    if(obj.data)
                                        $scope.totalItems = obj.data.length;

                                    console.log("length: " + $scope.totalItems);
                                },
                                function errorCallback(msg){
                                    //console.log(obj);
                                    $scope.message = msg;
                                }
                            );
                    }
                }

                $scope.newUser = {};
                $scope.saveNewUser = function(){
                    //console.log($scope.newUser);
                    $http.post('http://localhost:8080/api/addUser', $scope.newUser)
                        .then(
                            function successCallback(obj){
                                console.log(obj);
                                $scope.message = obj.data.message;
                                $scope.newUser = {};
                                $scope.listUsers();
                            },
                            function errorCallback(error){
                                console.log("[ERROR]: " + error);
                                $scope.message = error;
                            }
                        );
                };

                $scope.selectedUser = {};
                $scope.displayUserDetails = function(username){
                    //console.log('username: ' + username);
                    $http.get('http://localhost:8080/api/getUser?username=' + username)
                        .then(
                            function successCallback(obj){
                                //console.log(obj);
                                $scope.selectedUser = obj.data;
                            },
                            function errorCallback(obj){
                                //console.log(obj);
                                $scope.message = obj.data;
                            }
                        );
                };

                $scope.updateUser = function(){
                    $http.post('http://localhost:8080/api/updateUser', $scope.selectedUser)
                        .then(
                            function successCallback(obj){
                                console.log(obj);
                                $scope.message = obj.data.message;
                                $scope.selectedUser = {};
                                $scope.listUsers();
                            },
                            function errorCallback(error){
                                console.log("[ERROR]: " + error);
                                $scope.message = error;
                            }
                        );
                };

                $scope.removeUser = function(){
                    if($scope.selectedUser.username){
                      $http.post('http://localhost:8080/api/removeUser?username=' + $scope.selectedUser.username)
                          .then(
                              function successCallback(obj){
                                  $scope.message = obj.data.message;
                                  $scope.selectedUser = {};
                                  $scope.listUsers();
                              },
                              function errorCallback(error){
                                  console.log("[ERROR]: " + error);
                                  $scope.message = error;
                              }
                          );
                    }
                };

                $scope.paginate = function(value) {
                    console.log('paginate: ' + value);

                    var begin, end, index;
                    begin = ($scope.currentPage - 1) * $scope.numPerPage;
                    end = begin + $scope.numPerPage;
                    index = $scope.users.indexOf(value);
                    return (begin <= index && index < end);
                };

                $scope.currentPage = 1;
                $scope.numPerPage = 5;

                //Load all users
                $scope.listUsers();
                //$scope.populateDate();

            }
        ]
    );