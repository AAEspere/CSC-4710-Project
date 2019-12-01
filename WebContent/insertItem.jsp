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
<a href = "showQueries">SHOW ALL QUERIES</a>
<a href = "listFavoriteItems.jsp">Favorite Items</a>
<a href = "displayFavoriteUser.jsp">Favorite Sellers</a>
<a href = "searchItem.jsp">Search for Item</a>
<a href = "project3Queries">View Project 3 Query options</a>
<p>Insert an Item: please fill out the following information</div>
<div id = "registerBlock">
	<form action = "insertItem">
		<input type = "text" name = "itemName" placeholder = "Item Name" id = "LRFillin"><br>
		<input type = "text" name = "description" placeholder = "Description" id = "LRFillin"><br>
		<p>Please separate categories by comma</p>
		<input type = "text" name = "category" placeholder = "Category(s)" id = "LRFillin"><br>
		<input type = "text" name = "price" placeholder = "Price" id = "LRFillin"><br>
		<input type = "submit" value = "SUBMIT ITEM" id = "LRSubmit">
	</form>
	</div>
</body>
</html>