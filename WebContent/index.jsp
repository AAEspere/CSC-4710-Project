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
<h1 id = "titletext">Espere - CSC 4710 Project</h1>
</div>
<div class = "twocolumnXS floatright">
	<form action = "login.jsp" class = "inlineBlock">
	<input type = "submit" value = "Sign Up" id = "LogRegister">
	</form>
	<form action = "register.jsp" class = "inlineBlock">
	<input type = "submit" value = "Log in" id = "LogRegister">
	</form>
</div>

</div>
<div>
<form action = "initDatabase.jsp">
<input type = "submit" value = "Initialize Database">
</form>
</div>
</body>
</html>