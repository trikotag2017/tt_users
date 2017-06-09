<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>TT_Users</title>
    	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	</head> 

    

	<body>
		<div  ng-app="myApp" ng-controller="mainCtrl">
		<!--  -->oauth
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
	
	<!-- script src="resources/js/app.js"></script -->
	<!-- script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script -->
    
    <script  src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script  src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-resource.min.js"></script>
	<script  src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.min.js"></script>
	<script  src="https://cdnjs.cloudflare.com/ajax/libs/ngStorage/0.3.9/ngStorage.min.js"></script>
	<!-- script th:src="@{/resources/oauth-ng.js}"></script -->
	
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
