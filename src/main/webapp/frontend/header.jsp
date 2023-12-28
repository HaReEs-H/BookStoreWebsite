<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center">
	<div>
		<a href="${pageContext.request.contextPath}">
			<img src="images/WebsiteLogo.png" width="200" height="110" />
		</a>
	</div>
	<div>
		<form action="search" method="get">
			<input type="text" name="keyword" size="50"/>
		    &nbsp;
			<button type="submit">Search</button>
		</form>
		&nbsp;&nbsp;&nbsp;
		
		<c:if test="${loggedCustomer==null}">
			<a href="login">Sign In</a> &nbsp;|
		    <a href="register">Register</a> &nbsp;|&nbsp;
		</c:if>
		<c:if test="${loggedCustomer!=null}">
			<a href="view_profile">Welcome,${loggedCustomer.fullname}</a> &nbsp;|
			<a href="view_orders">My Orders</a> &nbsp;|
			<a href="logout">Logout</a> &nbsp;|
		</c:if>
		<a href="view_cart">Cart</a>
	</div>
	<div>
		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?id=${category.categoryId}">
				<font size="+1"><b><c:out value="${category.name}" /></b></font>
			</a>
			<c:if test="${not status.last}">
				&nbsp; | &nbsp;
			</c:if>
		</c:forEach>
	</div>
</div>