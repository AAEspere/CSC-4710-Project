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
<a href = "logout">log out</a>

<table border="1" cellpadding="5">
            <caption>List of Users</caption>
            <tr>
            	<th>Username</th>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Favorite?</th>
                <th>Blacklist</th>
            </tr>
            <c:forEach var="users" items = "${listUsers}">
            <tr>
            		<td><c:out value="${users.username}" /></td>
                    <td><c:out value="${users.userID}" /></td>
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
                    <td>
                    <form action = "addToBlacklist">
                    <input type = "hidden" value = "${users.username}" name = "username">
                    <input type = "submit" value = "BLACKLIST">
                    </form>
					</td>
                    </tr>
            </c:forEach>
		</table>
</body>
</html>