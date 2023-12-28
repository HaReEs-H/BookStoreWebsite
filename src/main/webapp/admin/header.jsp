<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin">
			<img src="../images/WebsiteLogo.png"  width="200" height="110" />
		</a>
	</div>
	<div>Welcome, <c:out value="${sessionScope.useremail}" />| <a href="logout">Logout</a></div><br/>
	<div id="headermenu">
	    <div>
	    	<a href="list_users">
	    	 	<img src="../images/users.png"  width="84" height="110"/><br/>Users
	    	</a> 
	    </div>
	    <div>
	    	<a href="list_category">
	    		<img src="../images/category.jpg" width="84" height="110"/><br/>Category
	    	</a>
	    </div>
		<div>
	    	<a href="list_books">
	    		<img src="../images/book.jpg" width="84" height="110"/><br/>Books
	    	</a> 
	    </div>
	    <div>
	    	<a href="list_customers">
	    		<img src="../images/customers.png" width="84" height="110"/><br/>Customers
	    	</a>
	    </div>
	    <div>
	    	<a href="list_reviews">
	    		<img src="../images/reviews.png" width="84" height="110"/><br/>Reviews
	    	</a>
	    </div>
	    <div>
	    	<a href="list_order">
	    		<img src="../images/orders.jpg" width="84" height="110"/><br/>Orders
	    	</a> 
	    </div>
	  </div>
</div>