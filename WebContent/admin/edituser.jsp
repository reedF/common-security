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
<sf:errors path="*"/>
<sf:form id="sf" method="POST" modelAttribute="userVo"  
        action="useredit.do">  
        <fieldset> 
            <legend>Edit</legend>  
            <table cellspacing="0">  
                <tr> 
                	<input type="hidden" value="<%=t %>" name="_check_token"/>
                    <sf:hidden path="user.id" />  
                    <sf:hidden path="user.password" />  
                    <td><sf:label path="user.account">Account:</sf:label>  
                    </td>  
                    <td><sf:input path="user.account" />  
                    </td>  
                    <td><sf:errors path="user.account" class="errors" />  
                    </td>  
                </tr>   
                 <tr>  
                    <td><sf:label path="user.name">Name:</sf:label>  
                    </td>  
                    <td><sf:input path="user.name" />  
                    </td>  
                    <td><sf:errors path="user.name" class="errors" />  
                    </td>  
                </tr>  
                <tr>
                    <td><sf:label path="groupIds">Groups:</sf:label>  
                    </td>  
                    <td>
                    <sf:checkboxes path="groupIds"  items="${all}" itemLabel="name" itemValue="id" />                  
                    </td>  
                    <td><sf:errors path="groupIds" class="errors" />  
                    </td>                  
                </tr>
                <tr>  
                    <td colspan="3"><input type="button" value="Edit"  
                        onClick="doSubmit()">  
                    </td>  
                </tr>  
            </table>  
        </fieldset>                      
</sf:form>   
</body>
</html>