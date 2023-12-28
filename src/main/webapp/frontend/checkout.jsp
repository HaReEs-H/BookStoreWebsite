<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
		<div align="center">
				<h2>Your Cart Details</h2>
				<c:if test="${message!=null}">
					<div align="center">
						<h4 class="message">${message}</h4>
					</div>
				</c:if>
				
				<c:set var="cart" value="${sessionScope['cart']}" />
				
				<c:if test="${cart.totalItems==0}">
					<h2>There's no items in your cart.</h2>
				</c:if>
				
				<c:if test="${cart.totalItems>0}">
						<div>
							<h2>Review Your Order Details <a href="view_cart">Edit</a></h2>
							<table border="1">
								<tr>
									<th>No</th>
									<th>Book</th>
									<th>Title</th>
									<th>Author</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Subtotal</th>
									<th>Actions</th>
								</tr>
								<c:forEach items="${cart.items}" var="item" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td>
											<img class="book-small" src="data:image/jpg;base64,${item.key.base64Image}"/>
				                        </td> 
				                        <td>
											<span id="book-title">${item.key.title}</span>
										</td>
										<td>${item.key.author}</td>
										<td>
											<input type="text" name="quantity${status.index+1}" value="${item.value}" size="5" readonly="readonly" />
										</td>
										<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
										<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
										<td><a href="remove_from_cart?book_id=${item.key.bookId}"><b>Remove</b></a></td>
									</tr>
								</c:forEach>
								
								<tr>
									<td colspan="7" align="right">
										<p>Number of copies: ${cart.totalQuantity}</p>
										<p>Subtotal: <fmt:formatNumber value="${cart.totalAmount}" type="currency" /></p>
										<p>Tax: <fmt:formatNumber value="${tax}" type="currency" /></p>
										<p>Shipping Fee: <fmt:formatNumber value="${shippingFee}" type="currency" /></p>
										<p>TOTAL: <fmt:formatNumber value="${total}" type="currency" /></p>
									</td>
								</tr>
							</table>
							<h2>Recipient Information</h2>
							<form action="place_order" method="post" id="orderForm">
								<table>
									<tr>
										<td>First Name:</td>
										<td><input type="text" name="firstname" value="${loggedCustomer.firstname}" /></td>
									</tr>
									<tr>
										<td>Last Name:</td>
										<td><input type="text" name="lastname" value="${loggedCustomer.lastname}" /></td>
									</tr>
									<tr>
										<td>Phone:</td>
										<td><input type="text" name="phone" value="${loggedCustomer.phone}" /></td>
									</tr>
									<tr>
										<td>Address Line 1:</td>
										<td><input type="text" name="address1" value="${loggedCustomer.addressLine1}" /></td>
									</tr>
									<tr>
										<td>Address Line 2:</td>
										<td><input type="text" name="address2" value="${loggedCustomer.addressLine2}" /></td>
									</tr>
									<tr>
										<td>City:</td>
										<td><input type="text" name="city" value="${loggedCustomer.city}" /></td>
									</tr>
									<tr>
										<td>State:</td>
										<td><input type="text" name="state" value="${loggedCustomer.state}" /></td>
									</tr>
									<tr>
										<td>Zip Code:</td>
										<td><input type="text" name="zipcode" value="${loggedCustomer.zipcode}" /></td>
									</tr>
									<tr>
										<td>Country:</td>
										<td>
											<select name="country" id="country">
												<c:forEach items="${mapCountries}" var="country">
													<option value="${country.value}"  
													<c:if test="${loggedCustomer.country eq country.value}">selected="selected"</c:if> > 
													${country.key}
													</option>
												</c:forEach>	
										   </select>
										</td>
									</tr>
								</table>
								<div>
									<h2>Payment</h2>
									Choose your payment method:
									&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="paymentMethod">
										<option value="Cash On Delivery">Cash On Delivery</option>
										<option value="paypal">PayPal or Credit Card</option>
									</select>
								</div>
								<div>
									<table class="normal">
										<tr><td>&nbsp;</td></tr>
										<tr>
											<td></td>
											<td><button type="submit">Place Order</button></td>
											<td><a href="${pageContext.request.contextPath}">Continue Shopping</a></td>
										</tr>
									</table>
								</div>
							</form>
						</div>
				</c:if>
				
		</div> 
<%@include file="footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#orderForm").click(function(){
			rules:{
				firstname:"required",
				lastname:"required",
				phone:"required",
				address1:"required",
				address2:"required",
				city:"required",
				state:"required",
				zipCode:"required",
				country:"required",
			},
			messages:{
				firstname:"Please enter firstname",
				lastname:"Please enter lastname",
				phone:"Please enter phone",
				address1:"Please enter address1",
				address2:"Please enter address2",
				city:"Please enter city",
				state:"Please enter state",
				zipCode:"Please enter zipcode",
				country:"Please enter country",
			}
		})
	})
	
</script>
</body>
</html>