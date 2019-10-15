<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Espere - CSC 4710 Project</title>
</head>
<body>

<h1>Espere - CSC 4710 Project</h1>
<button>Initialize Database</button>

<div align = "center">
<table border = "1" cellpadding = "5">

<caption>Items</caption>
	<tr>
		<th></th>
		<th>Item ID</th>
		<th>Item Description</th>
		<th>Date</th>
		<th>Item Price</th>
		<th>Item Category</th>
	</tr>
	<c:forEach var = "item" items = "${listItems}">
		<tr>
		<td><c:out value="${item.itemID}"/></td>
	 	<td><c:out value="${item.itemDescription}" /></td>
        <td><c:out value="${item.date}" /></td>
        <td><c:out value="${item.itemPrice}" /></td>
        <td><c:out value="${item.itemCategory}" /></td>
        <td></td>
		</tr>
	</c:forEach>
	</table>

<table border = "1" cellpadding = "5">
<caption>Users</caption>
	<tr>
		<th></th>
		<th>User ID</th>
		<th>Password</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
		<th>Age</th>
	</tr>
</table>
</div>



<form>
<button>Initialize Database</button>
</form>

</body>
</html>