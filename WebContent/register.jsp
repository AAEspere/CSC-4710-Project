<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="web.css">
<title>Insert title here</title>
</head>
<body>
<div id = "websiteheader">
<div class = "twocolumnXL floatleft">
<h1 id = "titletext"><a href = index.jsp class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
</div>
<div class = "clearboth" style ="height: 80px">
<p>Sign Up: Please fill all of the following blanks</div>
<div id = "registerBlock">
	<form action = "register">
		<input type = "text" name = "email" placeholder = "email" id = "LRFillin"><br>
		<input type = "password" name = "password" placeholder = "password" id = "LRFillin"><br>
		<input type = "text" name = "firstName" placeholder = "First Name" id = "LRFillin"><br>
		<input type = "text" name = "lastName" placeholder = "Last Name" id = "LRFillin"><br>
		<input type = "radio" name = "gender" value = "Male">Male<br>
		<input type = "radio" name = "gender" value = "Female">Female<br>
		<input type = "text" name = "age" placeholder = "age" id = "LRFillin"><br>
		<input type = "submit" value = "REGISTER" id = "LRSubmit">
	</form>
	</div>
</body>
</html>