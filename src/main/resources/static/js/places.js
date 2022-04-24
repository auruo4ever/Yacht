angular.module('demo', []).controller(
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
        $scope.editPlace = function(id) {
            $scope.hideform = false;
            $scope.edit = true;
            $scope.price = $scope.places[id].price;
            $scope.size = $scope.places[id].size;
            $scope.id = $scope.places[id].id;
        };

        $scope.savePlace = function() {
            var obj = {
                id : $scope.id,
                price : $scope.price,
                size : $scope.size
            };
            $scope.hideform = true;

            if($scope.rollNo==='')
                $http.post('http://localhost:8080/yachtMarina/place', obj, {
                    headers : {
                        'Content-Type' : 'application/json; charset=UTF-8'
                    },
                    'Accept' : 'application/json'
                }).then(function onSuccess(data) {
                    window.alert(JSON.stringify(data.data));
                    $http.get('http://localhost:8080/yachtMarina/place').then(
                        function(response) {
                            $scope.places = response.data;
                        });
                }, function onError(err) {
                    // do something on error
                });
            else
                $http.put('http://localhost:8080/yachtMarina/place', obj, {
                    headers : {
                        'Content-Type' : 'application/json; charset=UTF-8'
                    },
                    'Accept' : 'application/json'
                }).then(function onSuccess(data) {
                    window.alert(JSON.stringify(data.data));
                    $http.get('http://localhost:8080/yachtMarina/place').then(
                        function(response) {
                            $scope.places = response.data;
                        });
                }, function onError(err) {
                    // do something on error
                });
            $scope.rollNo=''
        };

        $scope.$watch('id', function() {
            $scope.test();
        });
        $scope.$watch('price', function() {
            $scope.test();
        });
        $scope.$watch('size', function() {
            $scope.test();
        });

        $scope.test = function() {
            $scope.incomplete = false;
            if ($scope.edit
                && (!$scope.price.length || !$scope.size.length)) {
                $scope.incomplete = true;
            }
        };
    });