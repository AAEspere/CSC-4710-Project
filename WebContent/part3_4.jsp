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
<h2>List the users who posted the most number of items since 5/1/2018</h2>
<form action = "postMostItems">
<input type = "submit" value = "List the users who posted the most number of items since 5/1/2018">
</form>
<br>
<br>
<table border="1" cellpadding="5">
            <caption>List of Users</caption>
            <tr>
            	<th>Username</th>
                <th>ID</th>
                <th>Favorite?</th>
            </tr>
            <c:forEach var="users" items = "${listUsers}">
            <tr>
            		<td><c:out value="${users.username}" /></td>
                    <td><c:out value="${users.userID}" /></td>
                    <td>
					<form action = "addFavoriteUser">
                    <input type = "hidden" value = "${users.userID}" name = "userID">
                    <input type = "submit" value = "Add to Favorites">
                    </form>
					</td>
                    </tr>
            </c:forEach>
		</table>
</div>
</body>
</html>