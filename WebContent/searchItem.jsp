<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="web.css">
<title>Espere - CSC 4710 Project</title>
</head>
<body>
<div id = "websiteheader">
<div class = "twocolumnXL floatleft">
<h1 id = "titletext"><a href = index.jsp class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
</div>
<div class = "clearboth" style ="height: 80px">
<h1>Item Search</h1>
<h2>Enter Item Category:</h2>
<form action = "searchItem" method = "post">
	<input type = "text" name = "category" placeholder = "Enter category(s)..." id = "LRFillin">
	<input type = "submit" value = "Submit Category">
</form>
<a href = "favoriteSellers.jsp">Favorite Sellers</a>
<a href = "favoriteItems.jsp">Favorite Items</a>
<form action = "sortExpensive">
<input type = "submit" value = "Sort by Expensive">
</form> 
</div>
<br>
<br>
<br>
<br>
<div>
<table border="1" cellpadding="5">
            <caption>List of Items</caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Date</th>
                <th>Price</th>
                <th>Categories</th>
            </tr>
            <c:forEach var="item" items = "${searchItem}">
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
		</div>
</body>
</html>