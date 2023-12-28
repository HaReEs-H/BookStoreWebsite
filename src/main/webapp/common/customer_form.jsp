
						<table class="form">
								<tr>
									<td align="right">E-mail:</td>
									<td align="left"><input type="text" name="email"  size="45"  id="email" value="${customer.email}" /></td>
								</tr>
								<tr>
									<td align="right">First Name:</td>
									<td align="left"><input type="text" name="firstName"  size="45"  id="firstName"  value="${customer.firstname}" /></td>
								</tr>
								<tr>
									<td align="right">Last Name:</td>
									<td align="left"><input type="text" name="lastName"  size="45"  id="lastName"  value="${customer.lastname}" /></td>
								</tr>
								<tr>
									<td align="right">Password:</td>
									<td align="left"><input type="password" name="password" size="45"  id="password"  value="${customer.password}" /></td>
								</tr>
								<tr>
									<td align="right">Confirm Password:</td>
									<td align="left"><input type="password" name="confirmPassword" size="45"  id="confirmPassword"  
									value="${customer.password}" /></td>
								</tr>
								<tr>
									<td align="right">Phone Number:</td>
									<td align="left">
										<input type="text" name="phone" size="45"  id="phone" value="${customer.phone}"/><br/>
									</td>
								</tr>
								<tr>
									<td align="right">Address Line 1:</td>
									<td align="left"><input type="text" name="address1" size="45"  id="address1"  value="${customer.addressLine1}" /></td>
								</tr>
								<tr>
									<td align="right">Address Line 2:</td>
									<td align="left"><input type="text" name="address2" size="45"  id="address2"  value="${customer.addressLine2}" /></td>
								</tr>
								<tr>
									<td align="right">City:</td>
									<td align="left"><input type="text" name="city" size="45"  id="city"  value="${customer.city}" /></td>
								</tr>
								<tr>
									<td align="right">State:</td>
									<td align="left"><input type="text" name="state" size="45"  id="state"  value="${customer.state}" /></td>
								</tr>
								<tr>
									<td align="right">Zip Code:</td>
									<td align="left"><input type="text" name="zipCode" size="45"  id="zipCode"  value="${customer.zipcode}" /></td>
								</tr>
								<tr>
									<td align="right">Country:</td>
									<td align="left">
										<select name="country" id="country">
											<c:forEach items="${mapCountries}" var="country">
												<option value="${country.value}" <c:if test="${customer.country eq country.value}">selected="selected"</c:if> >${country.key}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td colspan="2" align="center"> 
										<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
										<input type="button" value="Cancel" onclick="history.go(-1);" />
									</td>
								</tr>
						</table>