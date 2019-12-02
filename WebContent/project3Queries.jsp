<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="web.css">
<title>Espere - CSC 4710 Project</title>
</head>
<body>
<div id = "websiteheader">
<div class = "twocolumnXL floatleft">
<h1 id = "titletext"><a href = "showQueries" class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
<div class = "clearboth">
</div>
<a href = "showQueries">SHOW ALL QUERIES</a>
<a href = "insertItem.jsp">Insert an Item</a>
<a href = "displayFavoriteItem">Favorite Items</a>
<a href = "displayFavoriteUser">Favorite Sellers</a>
<a href = "searchItem.jsp">Search for Item</a>
<h2>Show Queries Menu: click on any of the links to be redirected</h2>
<table border = "1">
<tr><td><a href = "part3_2.jsp">List users who posted two items that are posted on the same day</a></td></tr>
<tr><td><a href = "part3_3.jsp">List all items posted by a user which only have "Excellent" or "Good" comments</a></td></tr>
<tr><td><a href = "part3_4.jsp">List the user who posted the most items since 5/01/18</a></td></tr>
<tr><td><a href = "part3_5.jsp">List other users who are both favorited by user X and Y</a></td></tr>
<tr><td><a href = "part3_6.jsp">List users who have no Excellent items</a></td></tr>
<tr><td><a href = "part3_7.jsp">List users who have never posted a Poor review</a></td></tr>
<tr><td><a href = "part3_8.jsp">List users who have only posted poor reviews</a></td></tr>
<tr><td><a href = "part3_9.jsp">List users who have never received any poor reviews</a></td></tr>
<tr><td><a href = "part3_10.jsp">List a user pair such that they always gave each other excellent
									Reviews for every item they posted.</a></td></tr>
</table>
</body>
</html>