<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp"/>
		<div align="center">
				<h2 class="pageheading">
				        <c:if test="${user!=null}">
				        		Edit User
				        </c:if>
				        <c:if test="${user==null}">
				        		Create New User
				        </c:if>
				</h2>
		</div>
		<div align="center">
		        <c:if test="${user!=null}">
		        <form action="update_user" method="post" id="userForm">
		        <input type="hidden"  name="user_Id" value="${user.userId}"/>
		        </c:if>
		        <c:if test="${user==null}">
		        <form action="create_user" method="post" id="userForm" >
		        </c:if>
						<table class="form">
								<tr>
									<td align="right">Email:</td>
									<td align="left"><input type="text" name="email"  size="20"  id="email" value="${user.email}" /></td>
								</tr>
								<tr>
									<td align="right">Full name:</td>
									<td align="left"><input type="text" name="full_name"  size="20"  id="fullname"  value="${user.fullName}" /></td>
								</tr>
								<tr>
									<td align="right">Password:</td>
									<td align="left"><input type="password" name="password" size="20"  id="password"  value="${user.password}" /></td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td colspan="2" align="center"> 
										<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
										<button id="buttonCancel">Cancel</button>
									</td>
								</tr>
						</table>
				</form>
		</div>
		<jsp:directive.include file="footer.jsp"/>
<script  type="text/javascript">

    $(document).ready(function(){
    	$("#userForm").validate({
    		rules:{
    			email:{
    				required:true,
    				email:true
    			},
    			full_name:"required",
    			password:"required",
    		},
    		messages:{
    			email:{
    				required:"Please enter email",
    				email:"Please enter an valid email address",
    			},
    			full_name:"Please enter fullname",
    			password:"Please enter password",
    		}
    	})
    	$("#buttonCancel").click(function(){
    		history.go(-1)
    	})
    })

</script>
</body>
</html>