<!DOCTYPE html>

 <html lang="en-US">

	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>TT_Users</title>
    	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	</head> 
 

	<body  ng-app="myApp" ng-controller="mainCtrl">
	<div class="container">

		<form class="form-horizontal">
			<oauth
		      site="http://trikotag.by/"
		      client-id="clientId"
		      redirect-uri="http://trikotag.by/restt/oauth/token"
		      scope="read"
		      template="oauthTemplate">
	    	</oauth>

		  <div class="form-group">
		    <label for="username">Username</label>
		    <div class="col-md-3">
		    	<input type="text" class="form-control" id="username" ng-model="data.username">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <div class="col-md-3">
		    	<input type="password" class="form-control" id="pwd" ng-model="data.password">
		    </div>
		  </div>
		  <div class="checkbox">
		    <label><input type="checkbox"> Remember me</label>
		  </div>
		  <button type="submit" class="btn btn-default" ng-click="login()">Login</button>
		  <a href="#" ng-click="login()">Login</a>
		</form> 
		
	</div>
	</body>

	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>

	<script src="resources/js/angular.min.js"></script>
	<script  src="resources/js/angular-resource.min.js"></script>
	<script  src="resources/js/angular-route.min.js"></script>
	<script  src="resources/js/ngStorage.min.js"></script>
	<script  src="resources/js/angular-cookies.min.js"></script>
	<script  src="resources/js/app.js"></script>
	

</html>