<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin</title>
<script type="text/javascript">
function doPage(to,total){
	//alert(to + "---" + total);
	var pageSize = document.getElementById("pageSize").value;
	//alert(pageSize);
	if (to > 0 && to <= total) {
		location.href = "listuser.do?pageSize=" + pageSize + "&current=" + to;
	}
}
function goPage(to,total){
	var pageSize = document.getElementById("pageSize").value;
	if (to > 0 && to <= total) {
		location.href = "listuser.do?pageSize=" + pageSize + "&current=" + to;
	}
}
function del(id){
    if(confirm('确实要删除该内容吗?')){
    	location.href ='userdel.do?id=' + id;
    }
}
</script>
</head>
<body>
<span style="color:red">Current user:<sec:authentication property="name"/></span><br>
List User
<table border="2">
<thead style="background: silver;">
<td>Id</td><td>Account</td><td>Name</td><td>Email</td><td>操作</td><td><a href="userview.do">Add</a>  <a href="../j_spring_security_logout">Logout</a></td>
</thead>
<tbody >
<c:forEach var="u" items="${users}">
<td>${u.id}</td><td>${u.account}</td><td>${u.name }</td><td>${u.email }</td>
<td><a href="userview.do?id=${u.id}">View</a>   <a href="useredit.do?id=${u.id}">Edit</a>   <a href="javascript:del(${u.id})">Del</a></td><tr>
</c:forEach>
</tbody>
<c:if test="${groupId == null}">
<tfoot>
<tr>
<td align="center" colspan="5">
<a href="javascript:doPage(1,${page.total});">首页</a>
<c:if test="${page.current <= 1 }">上一页</c:if>
<c:if test="${page.current > 1 }">
<a href="javascript:doPage(${page.current - 1},${page.total });">上一页</a>
</c:if>
<c:if test="${page.current >= page.total }">下一页</c:if>
<c:if test="${page.current < page.total }">
<a href="javascript:doPage(${page.current + 1},${page.total });">下一页</a>
</c:if>
<a href="javascript:doPage(${page.total },${page.total});">末页</a>
<!--跳转到<input id="current" type="text" size="1" value="${page.current}" onchange="goPage(this.value,${page.total })"/>/ Total Page:${page.total } -->
</td>
<td align="center" colspan="1">每页显示：<input id="pageSize" size="1" value="${page.length }"/> / Total results：${page.count }<td>
</tr>
</tfoot>
</c:if>
</table>
</body>
</html>