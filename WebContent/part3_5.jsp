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
<br>
<br>
<form action = "usersFavorited">
User X<br>
<select name = "usernameX">
<c:forEach var = "users" items = "${listUsers}">
<option value = "${users.userID }">${users.username }</option>
</c:forEach>
</select>
<br>
<br>
User Y<br>
<select name = "usernameY">
<c:forEach var = "users" items = "${listUsers}">
<option value = "${users.userID }">${users.username }</option>
</c:forEach>
</select>
<br>
<br>
<input type = "submit" value = "SUBMIT TWO USERS">
</form>
<br>
<br>
<table border="1" cellpadding="5">
            <caption>List of Users</caption>
            <tr>
            	<th>Username</th>
                <th>ID</th>
            </tr>
            <c:forEach var="favoriteUser" items = "${listFavUsers}">
            <tr>
            		<td><c:out value="${favoriteUser.favUserID}" /></td>
                    <td><c:out value="${favoriteUser.favUserUsername}" /></td>
                    </tr>
            </c:forEach>
		</table>
</div>
</body>
</html>