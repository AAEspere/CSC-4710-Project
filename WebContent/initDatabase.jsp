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
<a href = "project3Queries">View Project 3 Query options</a>
<form action = "sortExpensive">
<input type = "submit" value = "Sort by Expensive">
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
                    <td>
                    <form action = "addFavoriteItem">
                    <input type = "hidden" value = "${item.itemID}" name = "itemID">
                    <input type = "submit" value = "Add to Favorites">
                    </form>
                    </td>
                    <td>
                    <form action = "reviewID">
                    <input type = "hidden" value = "${item.itemID}" name = "itemID">
                    <input type = "submit" value = "Write Review">
                    </form>
                    </td>
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
                <th>Favorite?</th>
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
                    <td>
					<form action = "addFavoriteUser">
                    <input type = "hidden" value = "${users.userID}" name = "userID">
                    <input type = "submit" value = "Add to Favorites">
                    </form>
					</td>
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
            <c:forEach var="reviews" items = "${listReviews}">
            <tr>
                    <td><c:out value="${reviews.itemID}" /></td>
                    <td><c:out value="${reviews.userID}" /></td>
                    <td><c:out value="${reviews.score}" /></td>
                    <td><c:out value="${reviews.remark}" /></td>
                    </tr>
            </c:forEach>
		</table>
		
</body>
</html>