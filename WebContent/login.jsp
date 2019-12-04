<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="web.css">
<title>Espere - CSC 4710 Project</title>
</head>
<body>
<div id = "websiteheader">
<div class = "twocolumnXL floatleft">
<h1 id = "titletext"><a href = index.jsp class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
</div>
<div class = "clearboth" style ="height: 150px"></div>
<div id = "loginBlock">
	<form action = "login">
	<!--The name for this input used to be email, but I realized it makes more sense for user to use their email -->
		<input type = "text" name = "email" placeholder = "email" id = "LRFillin"><br>
		<input type = "password" name = "password" placeholder = "password" id = "LRFillin"><br>
		<input type = "submit" value = "LOGIN" id = "LRSubmit">
	</form>
	</div>
</body>
</html>