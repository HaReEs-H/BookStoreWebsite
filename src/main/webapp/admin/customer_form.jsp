<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
<link rel="stylesheet" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp"/>
		<div align="center">
				<h2 class="pageheading">
				       <!--Edit Mode-->
				        <c:if test="${book!=null}">
				        		Edit Customer
				        </c:if>
				        <!--New Mode-->
				        <c:if test="${book==null}">
				        		Create New Customer
				        </c:if>
				</h2>
		</div>
		<div align="center">
		        <c:if test="${customer!=null}">
		        <form action="update_customer" method="post" id="customerForm" >
		        <input type="hidden"  name="customer_Id" value="${customer.customerId}"/>
		        </c:if>
		        <c:if test="${customer==null}">
		        <form action="create_customer" method="post" id="customerForm">
		        </c:if>
				<jsp:directive.include file="../common/customer_form.jsp" />
				</form>
		</div>
		<jsp:directive.include file="footer.jsp"/>
<script  type="text/javascript" src="../js/customer-form.js">

</script>
</body>
</html>