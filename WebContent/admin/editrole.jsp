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
<sf:errors path="*"/> 
<sf:form id="sf" method="POST" modelAttribute="roleVo"  
        action="roleedit.do">   
        <fieldset>  
            <legend>Edit</legend>  
            <table cellspacing="0">  
                <tr> 
                    <sf:hidden path="role.id" />  
                    <td><sf:label path="role.name">Name:</sf:label>  
                    </td>  
                    <td><sf:input path="role.name" />  
                    </td>  
                    <td><sf:errors path="role.name" class="errors" />  
                    </td>  
                </tr>    

                <tr>
                    <td><sf:label path="resIds">Resources:</sf:label>  
                    </td>  
                    <td><sf:checkboxes items="${allres}" path="resIds"  itemLabel="name" itemValue="id" delimiter="<br>"/></td>  
                    <td><sf:errors path="groups" class="errors" />  
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