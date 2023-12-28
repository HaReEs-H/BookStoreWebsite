<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp"/>
		<div align="center">
				<h2 class="pageheading">
				       Edit My Profile
				</h2>
		</div>
		<div align="center">
		<form action="update_profile" method="post" id="customerForm" >
						<table class="form">
								<tr>
									<td align="right">E-mail:</td>
									<td align="left"><b>${loggedCustomer.email}</b> (Cannot be changed)</td>
								</tr>
								<tr>
									<td align="right">First Name:</td>
									<td align="left"><input type="text" name="firstName"  size="45"  id="firstName"
									 value="${loggedCustomer.firstname}"/></td>
								</tr>
								<tr>
									<td align="right">Last Name:</td>
									<td align="left"><input type="text" name="lastName"  size="45"  id="lastName"
									 value="${loggedCustomer.lastname}"/></td>
								</tr>
								<tr>
									<td align="right">Phone Number:</td>
									<td align="left">
										<input type="text" name="phone" size="45"  id="phone"
										value="${loggedCustomer.phone}"/><br/>
									</td>
								</tr>
								<tr>
									<td align="right">Address Line1:</td>
									<td align="left"><input type="text" name="address1" size="45"  id="address1"
									value="${loggedCustomer.addressLine1}"/></td>
								</tr>
								<tr>
									<td align="right">Address Line 2:</td>
									<td align="left"><input type="text" name="address2" size="45"  id="address2"
									value="${loggedCustomer.addressLine2}"/></td>
								</tr>
								<tr>
									<td align="right">City:</td>
									<td align="left"><input type="text" name="city" size="45"  id="city"/
									value="${loggedCustomer.city}"></td>
								</tr>
								<tr>
									<td align="right">State:</td>
									<td align="left"><input type="text" name="state" size="45"  id="state"/
									value="${loggedCustomer.state}"></td>
								</tr>
								<tr>
									<td align="right">Zip Code:</td>
									<td align="left"><input type="text" name="zipCode" size="45"  id="zipCode" 
									value="${loggedCustomer.zipcode}"/></td>
								</tr>
								<tr>
									<td align="right">Country:</td>
									<td align="left">
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
								<tr>
									<td colspan="2">
										<i>(Leave password fields blank if you don't want to change password)</i>
									</td>
								</tr>
								<tr>
									<td align="right">Password:</td>
									<td align="left"><input type="password" name="password" size="45"  id="password"/></td>
								</tr>
								<tr>
									<td align="right">Confirm Password:</td>
									<td align="left"><input type="password" name="confirmPassword" size="45"  id="confirmPassword"  /></td>
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
    	$("#customerForm").validate({
    		rules:{
    			email:{
    				required:true,
    				email:true
    			},
    			fullName:"required",
    			confirmPassword:{
    				equalTo:"#password",
    			},
    			phone:"required",
    			address:"required",
    			city:"required",
    			zipCode:"required",
    			country:"required",
    		},
    		messages:{
    			email:{
    				required:"Please enter e-mail address",
    				email:"Please enter a valid e-mail address"
    			},
    			fullName:"Please enter full name",
    			confirmPassword:{
    				equalTo:"Confirm password does not match password",
    			},
    			phone:"Please enter phone number",
    			address:"Please enter address",
    			city:"Please enter city",
    			zipCode:"Please enter zip code",
    			country:"Please enter country",
    		}
    	});
    	$("#buttonCancel").click(function(){
    		history.go(-1)
    	});
    })
    
    function showImageThumbnail(fileInput){
    	var file=fileInput.files[0];
    	var reader=new FileReader();
    	reader.onload=function(e){
    		$('#thumbnail').attr('src',e.target.result);
    	};
    	reader.readAsDataURL(file);
    }
</script>
</body>
</html>