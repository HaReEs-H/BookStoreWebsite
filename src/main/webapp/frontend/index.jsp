<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online BookStore 📔</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="center">
<div>
	<h2>New Books:</h2>
	<c:forEach items="${listNewBooks}" var="book">
		<jsp:directive.include file="book_group.jsp" />
	</c:forEach>
</div>
<div class="next-row">
	<h2>Best-Selling Books:</h2>
	<c:forEach items="${listBestSellingBooks}" var="book">
		<jsp:directive.include file="book_group.jsp" />
	</c:forEach>
</div class="next-row">
<div align="center" style="clear:both">
	<h2>Most-favored Books:</h2>
	<c:forEach items="${listFavoredBooks}" var="book">
		<jsp:directive.include file="book_group.jsp" />
	</c:forEach>
</div>
<br/><br/>
</div>
<%@include file="footer.jsp"%>
</body>
</html>