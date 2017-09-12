var consumeRestApiApp = angular.module("consumeRestApiApp", []);
consumeRestApiApp.controller("consumeRestApiController",
    function($scope, $http) {

        $scope.users = [
            {username: "john", firstName: "John", lastName: "Craig", email: "johnCraig@gmail.com"},
            {username: "mcraig", firstName: "Mark", lastName: "Craig", email: "markCraig@gmail.com"},
            {username: "eblade", firstName: "Eric", lastName: "Blade", email: "ericBlade@gmail.com"}
        ];

        // for message alert
        $scope.message = "";
        $scope.clearMessage = function(){
            $scope.message = "";
        };


        $scope.newUser = {};
        $scope.saveNewUser = function(){
            // console.log($scope.newUser);
            $scope.users.push($scope.newUser);
            $scope.newUser = {};
            $scope.message = "New user added successfully!";
        };

        $scope.selectedUser = {};
        $scope.displayUserDetails = function(user){
            // console.log(user);
            $scope.selectedUser = user;
        };

        $scope.updateUser = function(){
            $scope.message = "User updated successfully!";
        };

        $scope.removeUser = function(){
            $scope.users.splice($scope.users.indexOf($scope.selectedUser), 1);
            $scope.message = "User deleted successfully!";
        };
    }
);