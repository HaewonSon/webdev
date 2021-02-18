<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="styles/styles.css">
<head>
<meta charset="UTF-8">
<title>LogIn </title>
</head>
<body>


	<header class="welcome-header">
	<h2 class="welcome-header_title">Log In </h2>
	</header>
	<br/>
	<div>
	<form id="login-form" action="LoginServlet" method="post">
		<input type="text" name="id" placeholder="ID"/>
		<br>
		${idMsg }
		<br/>
		<br/>
		<input type="password" name="password" placeholder="Password"/>
		<br/>
		${pwMsg }
		<br/>
		<br/>
		<br/>
		<input type="submit" value="Sign In"/>
	<a href="JoinServlet">Create Account</a>
	</form>
	
	</div>
	 <script
      src="https://kit.fontawesome.com/6478f529f2.js"
      crossorigin="anonymous"
    ></script>
</body>
</html>