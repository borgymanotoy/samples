var phonebookApp = angular.module("phonebookApp", []);
phonebookApp.controller("phonebookController",
    function($scope, $http) {

        $http.get('/api/listContacts?v=' + Date.now())
            .then(
                function successCallback(obj){
                    console.log(obj);
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

        $scope.searchKey = "";
        $scope.listContacts = function () {
            $http.get('/api/listContacts?v=' + Date.now())
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
            console.log($scope.newContact);
            //For now, we will just default all the password to 'letmein123' since we have not implemented the authentication
            $scope.newContact.password = "letmein123";
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
        $scope.displayContactDetails = function(_id){
            console.log('_id: ' + _id);
            if(_id && ''!=_id){
                $http.get('/api/getContact?username=' + _id)
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
            }
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

        $scope.removeContact = function(){
            if($scope.selectedContact._id){
                $http.post('/api/removeContact?username=' + $scope.selectedContact._id)
                    .then(
                        function successCallback(obj){
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