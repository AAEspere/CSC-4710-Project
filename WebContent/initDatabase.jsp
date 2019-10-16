<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ page import = "csc4710_Espere_part1.InitDatabase" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="web.css">
<title>Espere - CSC 4710 Project</title>
</head>
<body>
<div>
<h1>Espere - CSC 4710 Project</h1>
</div>
<div>
<%
InitDatabase DB1 = new InitDatabase();
DB1.InitDatabaseButton();
%>
</div>
</body>
</html>