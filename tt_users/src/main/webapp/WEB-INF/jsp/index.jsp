<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html lang="en-US">

	<body>
		<div  ng-app="myApp" ng-controller="mainCtrl">
		<oauth
	      site="http://localhost:8080/spring-security-oauth-server"
	      client-id="clientId"
	      redirect-uri="http://localhost:8080/spring-security-oauth-ui-implicit/index"
	      scope="read"
	      template="oauthTemplate">
	    </oauth>
		<h1>Login</h1>
		<label>Username</label><input ng-model="data.username"/>
		<label>Password</label><input type="password" ng-model="data.password"/>
		<a href="#" ng-click="login()">Login</a>
		</div>
	</body>


	<script src="resources/js/angular.min.js"></script>
	<script  src="resources/js/angular-resource.min.js"></script>
	<script  src="resources/js/angular-route.min.js"></script>
	<script  src="resources/js/ngStorage.min.js"></script>
	<script  src="resources/js/angular-cookies.min.js"></script>
	
	<script>
	
	var app = angular.module('myApp', ["ngResource","ngRoute","ngCookies"]);
	app.controller('mainCtrl', 
	  function($scope, $resource, $http, $httpParamSerializer, $cookies) {
	     
	    $scope.data = {
	        grant_type:"password", 
	        username: "", 
	        password: "", 
	        client_id: "clientIdPassword"
	    };
	    $scope.encoded = btoa("clientIdPassword:secret");
	     
	    $scope.login = function() {   
	        var req = {
	            method: 'POST',
	            url: "http://trikotag.by/restt/oauth/token",
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
	</script>

</html>