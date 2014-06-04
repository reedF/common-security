<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin</title>
</head>
<body>
<form:errors></form:errors> 
<form  method="post" action="modeladd.do" name="model">   
    Name:<input id="name" name="name" type="text" /><br>  
    Url:<input id="url" name="url" type="text"/><br>  
    <input type="submit" value="Submit" />
</form>  
</body>
</html>