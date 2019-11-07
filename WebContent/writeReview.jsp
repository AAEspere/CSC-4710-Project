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
<h1 id = "titletext"><a href = "showQueries" class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
</div>
<div class = "clearboth" style ="height: 80px">
<h1>Review an Item</h1>
<p>Fill out the following forms in order to write a review.</p>
<p>WARNING: you can only write five reviews per day. 
<div id = "registerBlock">
	<form action = "addReview">
		<select name = "remark">
		<option>Excellent</option>
		<option>Good</option>
		<option>Fair</option>
		<option>Poor</option>
		</select>
		<br><br>
		<input type = "text" name = "review" placeholder = "Write a review..." id = "LRSubmit">
		<br><br>
		<input type = "submit" value = "SUBMIT REVIEW" id = "LRSubmit">
	</form>
	</div>
</body>
</html>