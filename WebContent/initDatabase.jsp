<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ page import = "csc4710_Espere_part1.InitDatabase"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="web.css">
<title>Espere - CSC 4710 Project</title>
</head>
<body>
<div id = "websiteheader">
<div class = "twocolumnXL floatleft">
<h1 id = "titletext"><a href = index.jsp class = "tdnone">Espere - CSC 4710 Project</a></h1>
</div>
<div class = "twocolumnXS floatright">
	<form action = "register.jsp" class = "inlineBlock">
	<input type = "submit" value = "Sign Up" id = "LogRegister">
	</form>
	<form action = "login.jsp" class = "inlineBlock">
	<input type = "submit" value = "Log in" id = "LogRegister">
	</form>
</div>
</div>

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
            <c:forEach var="item" items = "${listItem}">
            <tr>
                    <td><c:out value="${item.itemID}" /></td>
                    <td><c:out value="${item.itemTitle}" /></td>
                    <td><c:out value="${item.itemDescription}" /></td>
                    <td><c:out value="${item.itemDate}" /></td>
                    <td><c:out value="${item.Price}" /></td>
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
		
</body>
</html>