<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin</title>
</head>
<body>
<span style="color:red">Current user:<sec:authentication property="name"/></span><br>
List
<table border="2">
<thead style="background: silver;">
<td>Id</td><td>Name</td><td>操作</td><td><a href="role.jsp">Add</a>  <a href="../j_spring_security_logout">Logout</a></td>
</thead>
<tbody >
<c:forEach var="u" items="${roles}">
<td>${u.id}</td><td>${u.name}</td><td><a href="roleview.do?id=${u.id}">View</a>   <a href="roleedit.do?id=${u.id}">Edit</a>   <a href="roledel.do?id=${u.id}">Del</a></td><tr>
</c:forEach>
</tbody>
</table>
</body>
</html>