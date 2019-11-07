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
<h1>Item Search</h1>
<h2>Enter Item Category:</h2>
<form action = "searchItem">
	<input type = "text" name = "category" placeholder = "Enter category(s)..." id = "LRFillin">
	<input type = "submit" value = "SUBMIT CATEGORY">
</form>
<a href = "showQueries">SHOW ALL QUERIES</a>
<a href = "listFavoriteItems.jsp">Favorite Items</a>
<a href = "displayFavoriteUser">Favorite Sellers</a>
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
		</div>
</body>
</html>