<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin</title>
</head>
<body>
<span style="color:red">Current user:<sec:authentication property="name"/></span><br>
List User
<table border="2">
<thead style="background: silver;">
<td>Id</td><td>Account</td><td>Name</td><td>Email</td><td>操作</td><td><a href="userview.do">Add</a>  <a href="../j_spring_security_logout">Logout</a></td>
</thead>
<tbody >
<#if users??>
	<#list users as u>
		<tr>
		<td>${(u.id)!}</td>
		<td>${(u.account)!}</td>
		<td>${(u.name)!}</td>
		<td>${(u.email)!}</td>
		<td><a href="userview?id="${(u.id)!}>View</a>   <a href="useredit?id="${(u.id)!}>Edit</a></td>
		</tr>
	</#list>
</#if>
</tbody>
<tfoot>
<tr>
<td align="center" colspan="5">
	<#import "pagenation.ftl" as h>
	<#assign url = "${r'listuser?t=1&p='}"  />
	<@h.pager __url="${url}" __page="${page.current?number}"  __page_total="${page.total?number}" />
</td>
</tr>
</tfoot>
</table>
</body>
</html>