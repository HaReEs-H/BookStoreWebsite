<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Categories</title>
<link rel="stylesheet" href="../css/style.css" >
<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3><a href="category_form.jsp">Create new Category</a></h3>
	</div>
	<c:if test="${message!=null}">
			<div align="center">
			<h4 class="message">${message}</h4>
	</div>
	</c:if>
	<div align="center">
		<table border="1">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listCategory}" varStatus="status">
			<tr>
				<td>${status.index}</td>
				<td>${cat.categoryId}</td>
				<td>${cat.name}</td>
				<td>
					<a href="edit_category?id=${cat.categoryId}">Edit</a> &nbsp;
					<a href="javascript:void(0)" class="deleteLink" id="${cat.categoryId}">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script>
			$(document).ready(function(){
				$(".deleteLink").each(function(){
					$(this).on("click",function(){
						categoryId=$(this).attr("id")
						if(confirm("Are you sure you want to delete the categroy with ID "+categoryId+" ?")){
							window.location="delete_category?id="+categoryId
						}
					})
				})
			})
	</script>
</body>
</html>