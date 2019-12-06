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
<a href = "project3Queries.jsp">View Project 3 Query options</a>
<a href = "logout">log out</a>
<h2>List all items posted by user X, such that all comments are "Excellent" or "Good"</h2>
<form action = "excellentGood" method = "post">
<input type = "text" name = "username" placeholder = "Enter username..." id = "LRFillin">
<input type = "submit" value = "SUBMIT USERNAME">
</form>
<table border="1" cellpadding="5">
            <caption>List of Items</caption>
            <tr>
            	<th>Username</th>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Date</th>
                <th>Price</th>
                <th>Categories</th>
                <th>Favorite?</th>
                <th>Write a Review</th>
            </tr>
            <c:forEach var="item" items = "${listItem}">
            <tr>
            		<td><c:out value="${item.username}" /></td>
                    <td><c:out value="${item.itemID}" /></td>
                    <td><c:out value="${item.itemTitle}" /></td>
                    <td><c:out value="${item.itemDescription}" /></td>
                    <td><c:out value="${item.date}" /></td>
                    <td><c:out value="${item.itemPrice}" /></td>
                    <td><c:out value="${item.itemCategory}"/></td>
                    </tr>
            </c:forEach>
		</table>
</div>
</body>
</html>