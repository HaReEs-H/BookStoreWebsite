<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
		<div align="center">
				<form id="reviewForm">
					<table class="normal" width="60%">
						<tr>
							<td><h3>You already wrote a review for this book</h3></td>
							<td><h2>${loggedCustomer.fullname}</h2></td>
						</tr>
						</tr>
							<td colspan="3"><hr/></td>
						<tr>
						</tr>
							<td>
							<span id="book-title">${book.title}</span>
							<br/><br/>
							<img class="book-large"
				src="data:image/jpg;base64,${book.base64Image}"/>
							</td>
							<td>
								<div id="rateYo"></div>
								<br/><br/>
								<input type="text" name="headline" size="60" readonly="readonly" 
								value="${review.headline}"/>
								<br/><br/>
								<textarea name="comment" cols="70" rows="10" readonly="readonly"
								>${review.comment}</textarea>
							</td>
						<tr>
					</table>
				</form>
		</div> 
<%@include file="footer.jsp"%>
		<script>
				$(document).ready(function(){
					
					})
					$("#rateYo").rateYo({
					    starWidth: "40px",
					    fullStar:true,
					    rating:${review.rating},
					    readOnly:true
					  });
					$("#buttonCancel").click(function(){
			    		history.go(-1)
			    	});
				})
		</script>
</body>
</html>