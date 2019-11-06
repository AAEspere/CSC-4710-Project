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
<h1 id = "titletext"><a href = initDatabase.jsp class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
<div class = "clearboth">
</div>

<a href = "insertItem.jsp">Insert an Item</a>
<a href = "favoriteSellers.jsp">Favorite Sellers</a>
<a href = "favoriteItems.jsp">Favorite Items</a>
<form action = "sortExpensive">
<input type = "submit" value = "Sort by Expensive">
</form> 

<table border="1" cellpadding="5">
            <caption>List of Items</caption>
            <tr>
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
                    <td><c:out value="${item.itemID}" /></td>
                    <td><c:out value="${item.itemTitle}" /></td>
                    <td><c:out value="${item.itemDescription}" /></td>
                    <td><c:out value="${item.date}" /></td>
                    <td><c:out value="${item.itemPrice}" /></td>
                    <td><c:out value="${item.itemCategory}"/></td>
                    <td><form action = "addFavorite"><input type = "submit" value = "Add to Favorites"></form></td>
                    <td><a href = "addReview.jsp">Write a Review</a>
                    </tr>
            </c:forEach>
		</table>
		<br>
		<br>
<table border="1" cellpadding="5">
            <caption>List of Users</caption>
            <tr>
                <th>ID</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Age</th>
            </tr>
            <c:forEach var="users" items = "${listUsers}">
            <tr>
                    <td><c:out value="${users.userID}" /></td>
                    <td><c:out value="${users.pass}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.email}"/></td>
                    <td><c:out value="${users.gender}"/></td>
                    <td><c:out value="${users.age}"/></td>
                    </tr>
            </c:forEach>
		</table>
		<br>
		<br>
<table border="1" cellpadding="5">
            <caption>List of Reviews</caption>
            <tr>
                <th>Item ID</th>
                <th>User ID</th>
                <th>Score</th>
                <th>Remark</th>
            </tr>
            <c:forEach var="users" items = "${listReviews}">
            <tr>
                    <td><c:out value="${review.itemID}" /></td>
                    <td><c:out value="${review.userID}" /></td>
                    <td><c:out value="${review.score}" /></td>
                    <td><c:out value="${review.remark}" /></td>
                    </tr>
            </c:forEach>
		</table>
		
</body>
</html>