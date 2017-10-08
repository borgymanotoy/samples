var consumeRestApiApp = angular.module("consumeRestApiApp", []);
consumeRestApiApp.controller("consumeRestApiController",
    function($scope, $http) {

        $http.get('/api/listContact?v=' + Date.now())
            .then(
                function successCallback(obj){
                    //console.log(obj);
                    $scope.contacts = obj.data;
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

        $scope.listContacts = function () {
            $http.get('/api/listContact')
                .then(
                    function successCallback(obj){
                        $scope.contacts = obj.data;
                    },
                    function errorCallback(msg){
                        //console.log(obj);
                        $scope.message = msg;
                    }
                );
        };

        $scope.newContact = {};
        $scope.saveNewContact = function(){
            //console.log($scope.newContact);
            $http.post('/api/addContact', $scope.newContact)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.newContact = {};
                        $scope.listContacts();
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + error);
                        $scope.message = error;
                    }
                );
        };

        $scope.selectedContact = {};
        $scope.displayContactDetails = function(id){
            //console.log('username: ' + username);
            $http.get('/api/getContact?id=' + id)
                .then(
                    function successCallback(obj){
                        //console.log(obj);
                        $scope.selectedContact = obj.data;
                    },
                    function errorCallback(obj){
                        //console.log(obj);
                        $scope.message = obj.data;
                    }
                );
        };

        $scope.updateContact = function(){
            $http.post('/api/updateContact', $scope.selectedContact)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.selectedContact = {};
                        $scope.listContacts();
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + error);
                        $scope.message = error;
                    }
                );
        };

        $scope.removeContact = function(id){
            console.log(id);
            if(id){
              $http.post('/api/removeContact?id=' + id)
                  .then(
                      function successCallback(obj){
                          console.log(obj);
                          $scope.message = obj.data.message;
                          $scope.selectedContact = {};
                          $scope.listContacts();
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