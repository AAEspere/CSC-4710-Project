<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ page import = "csc4710_Espere_part1.InitDatabase"%>
      <%@ page import = "csc4710_Espere_part1.ControlServlett" %>
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
<%
InitDatabase DB1 = new InitDatabase();
%>

<table border="1" cellpadding="5">
            <caption>List of Items</caption>
            <tr>
            	<th>Item</th>
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
</div>
</body>
</html>