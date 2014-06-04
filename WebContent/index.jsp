<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

Menu <br>
<sec:authorize ifAllGranted="查看用户列表 ">
<a href="admin/listuser.do">List User</a><br>
</sec:authorize>

<a href="admin/listmodel.do">List Model</a><br>

<a href="admin/listresource.do">List Resource</a><br>

<a href="admin/listgroup.do">List Group</a><br>

<a href="admin/listrole.do">List Role</a><br>

<br>
Hello! <span style="color:red">Current user:<sec:authentication property="name"/></span>
<br>
<sec:authentication property="principal" />
<br>
<a href="j_spring_security_logout">Logout</a>
</body>
</html>