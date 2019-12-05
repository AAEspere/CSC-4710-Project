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
</div>
<div class = "clearboth" style ="height: 80px">
<a href = "showQueries">SHOW ALL QUERIES</a>
<a href = "insertItem.jsp">Insert an Item</a>
<a href = "displayFavoriteItem">Favorite Items</a>
<a href = "displayFavoriteUser">Favorite Sellers</a>
<a href = "searchItem.jsp">Search for Item</a>
<a href = "project3Queries.jsp">View Project 3 Query options</a>
<form action = "sortExpensive">
<input type = "submit" value = "Sort by Expensive">
</form> 
</div>
<h1>Favorite Sellers</h1>
<div>
<table border="1" cellpadding="5">
            <caption>List of Favorite Sellers</caption>
            <tr>
            	<th>Favorite Seller ID</th>
                <th>Delete Favorite</th>
                <th>See Profile</th>
            </tr>
            <c:forEach var="favoriteUser" items ="${favUsers}">
            <tr>
                    <td><c:out value="${favoriteUser.favUserID}" /></td>
                    <td>
					<form action = "deleteUser">
                    <input type = "hidden" value = "${favoriteUser.favUserID}" name = "favUserID">
                    <input type = "submit" value = "Delete from Favorites">
                    </form>
                    </td>
                    <td>
                    <form action = "displayUser">
                    <input type = "hidden" value = "${favoriteUser.favUserID}" name = "favUserID">
                    <input type = "submit" value = "See User Profile">
                    </form>
                    </td>
                    </tr>
            </c:forEach>
		</table>
		</div>
</body>
</html>