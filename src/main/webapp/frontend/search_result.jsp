<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results for ${keyword} - Online BookStore 📔</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="center">
	<c:if test="${fn:length(result)==0}">
	<h2>No Results for "${keyword}":</h2>
    </c:if>

<c:if test="${fn:length(result)>0}">
<div class="book-group" >
	<center><h2>Results for "${keyword}":</h2></center>
	<c:forEach items="${result}" var="book">
	<div>
		<div id="search-image">
			<div>
				<a href="view_book?id=${book.bookId}">
					<img class="book-small"
					src="data:image/jpg;base64,${book.base64Image}" />
				</a>
			</div>
		<div>
		<div id="search-description">
			<div>
				<h2><a href="view_book?id=${book.bookId}">
					<b>${book.title}</b>
				</a></h2>
			</div>
			<div>Rating *****</div>
			<div><i>by ${book.author}</i></div>
			<div>
				<p>${fn:substring(book.description,0,100)}.......</p>
			</div>
		</div>
		<div id="search-price">
			<h3><b>$${book.price}</b></h3>
			<h3><a href="">Add to Cart</a></h3>
		</div>
	</div>
	</c:forEach>
</div>
</c:if>
</div>
<%@include file="footer.jsp"%>
</body>
</html>