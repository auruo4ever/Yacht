var app = angular.module('demo', []);

app.controller(
    'orderCtrl',
    function($scope, $http) {
        $http.get('http://localhost:8080/yachtMarina/orders').then(
            function(response) {
                $scope.ordres = response.data;
            });
        $scope.bookingDateStart = '';
        $scope.bookingDateEnd = '';
        $scope.id = '';
        $scope.name = '';
        $scope.mail = '';
        $scope.edit = true;
        $scope.error = false;
        $scope.complete = false;
        $scope.place_id = 1;

        $scope.saveOrder = function() {
            var obj = {
                id : $scope.id,
                bookingDateStart : $scope.bookingDateStart,
                bookingDateEnd : $scope.bookingDateEnd,
                name : $scope.name,
                mail : $scope.mail,
                place : {
                    "id": $scope.place_id,
                }
            };

            if($scope.id==='')
                $http.post('http://localhost:8080/yachtMarina/order', obj, {
                    headers : {
                        'Content-Type' : 'application/json; charset=UTF-8'
                    },
                    'Accept' : 'application/json'
                }).then(function onSuccess(data) {
                    $http.get('http://localhost:8080/yachtMarina/order').then(
                        function(response) {
                            $scope.orders = response.data;
                        });
                }, function onError(err) {
                    console.error("Error in posting")
                });
            else
                $http.put('http://localhost:8080/yachtMarina/order', obj, {
                    headers : {
                        'Content-Type' : 'application/json; charset=UTF-8'
                    },
                    'Accept' : 'application/json'
                }).then(function onSuccess(data) {
                    $http.get('http://localhost:8080/yachtMarina/order').then(
                        function(response) {
                            $scope.orders = response.data;
                        });
                }, function onError(err) {
                    // do something on error
                });
            $scope.id=''
        };
    });

app.controller(
    'placeCtrl',
    function($scope, $http) {
        $http.get('http://localhost:8080/yachtMarina/place').then(
            function(response) {
                $scope.places = response.data;
            });
        $scope.price = '';
        $scope.size = '';
        $scope.id = '';
        $scope.edit = true;
        $scope.error = false;
        $scope.incomplete = false;
        $scope.hideform = true;
        $scope.editPlaceN = function() {
            $scope.hideform = false;
            $scope.edit = true;
            $scope.incomplete = true;
            $scope.rollNo = '';
            $scope.name = '';
            $scope.surname = '';
        };

    });