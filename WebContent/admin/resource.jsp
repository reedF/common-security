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
<sf:form id="sf" method="POST" modelAttribute="resourceVo"  
        action="resourceadd.do">   
        <fieldset>  
            <legend>Add</legend>  
            <table cellspacing="0">  
                 <tr> 
                    <td><sf:label path="resource.name">Name:</sf:label>  
                    </td>  
                    <td><sf:input path="resource.name" />  
                    </td>  
                    <td><sf:errors path="resource.name" class="errors" />  
                    </td>  
                </tr>             
                <tr> 
                    <td><sf:label path="resource.url">Url:</sf:label>  
                    </td>  
                    <td><sf:input path="resource.url" />  
                    </td>  
                    <td><sf:errors path="resource.url" class="errors" />  
                    </td>  
                </tr>    

                <tr>
                    <td><sf:label path="resource.modelid">Model:</sf:label>  
                    </td>  
                    <td><sf:select items="${all}" path="resource.modelid"  itemLabel="name" itemValue="id" />  
                    </td>  
                    <td><sf:errors path="resource.modelid" class="errors" />  
                    </td>                  
                </tr>
 
                <tr>  
                    <td colspan="3"><input type="button" value="ADD"  
                        onClick="doSubmit()">  
                    </td>  
                </tr>  
            </table>                       
        </fieldset>              
</sf:form>  
</body>
</html>