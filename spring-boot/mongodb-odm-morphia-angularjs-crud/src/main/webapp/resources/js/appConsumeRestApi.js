var consumeRestApiApp = angular.module("consumeRestApiApp", []);
consumeRestApiApp.controller("consumeRestApiController",
    function($scope, $http) {

        $http.get('/api/listUsers?v=' + Date.now())
            .then(
                function successCallback(obj){
                    //console.log(obj);
                    $scope.users = obj.data;
                },
                function errorCallback(msg){
                    console.log(msg);
                    $scope.message = msg;
                }
            );

        // for message alert
        $scope.message = "";
        $scope.clearMessage = function(){
            $scope.message = "";
        };

        $scope.listUsers = function () {
            $http.get('/api/listUsers')
                .then(
                    function successCallback(obj){
                        $scope.users = obj.data;
                    },
                    function errorCallback(msg){
                        //console.log(obj);
                        $scope.message = msg;
                    }
                );
        };

        $scope.newUser = {};
        $scope.saveNewUser = function(){
            //console.log($scope.newUser);
            $http.post('/api/addUser', $scope.newUser)
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
            $http.get('/api/getUser?username=' + username)
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
            $http.post('/api/updateUser', $scope.selectedUser)
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

        $scope.removeUser = function(username){
            console.log(username);
            if(username){
              $http.post('/api/removeUser?username=' + username)
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
            }
        };
    }
);