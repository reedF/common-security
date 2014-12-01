<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin</title>
<script type="text/javascript">  
    function doSubmit() {  
        var sf = document.getElementById("sf");  
        sf.submit();  
    }  
</script> 
</head>
<body>
<%
String t = (String)request.getAttribute("_check_token");
System.out.println(t);
%>
<form:errors></form:errors> 
<form  method="post" action="useradd.do" name="user">   
	<input type="hidden" value="<%=t %>" name="_check_token"/>
    Account:<input id="account" name="account" type="text" /><br> 
    Password:<input id="password" name="password" type="password"/> <br> 
    Email:<input id="email" name="email" type="text"/><br>  
    <input type="submit" value="Submit" />
</form> 


View:<br>
<c:if test="${user != null}">
Account: <c:out value="${user.account}"></c:out><br>
Name: <c:out value="${user.name}"></c:out><br>
</c:if>
</body>
</html>