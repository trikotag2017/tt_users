var app = angular.module('myApp', ["ngResource","ngRoute","ngCookies"]);
app.controller('mainCtrl', 
  function($scope, $resource, $http, $httpParamSerializer, $cookies) {
     
    $scope.data = {
        grant_type:"password", 
        username: "", 
        password: "", 
        client_id: "bvv"
    };
    $scope.encoded ="clientIdPassword:12345678";
     
    $scope.login = function() {   
        var req = {
            method: 'POST',
            url: "https://trikotag.by/restt/oauth/token",
            headers: {
                "Authorization": "Basic " + $scope.encoded,
                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
            },
            data: $httpParamSerializer($scope.data)
        }
        $http(req).then(function(data){
            $http.defaults.headers.common.Authorization = 
              'Bearer ' + data.data.access_token;
            $cookies.put("access_token", data.data.access_token);
            window.location.href="index";
        });   
   }    
});