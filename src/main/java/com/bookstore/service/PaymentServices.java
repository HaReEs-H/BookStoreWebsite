//package com.bookstore.service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import com.bookstore.entity.Book;
//import com.bookstore.entity.BookOrder;
//import com.bookstore.entity.Customer;
//import com.bookstore.entity.OrderDetail;
//import com.paypal.api.payments.Amount;
//import com.paypal.api.payments.Details;
//import com.paypal.api.payments.Item;
//import com.paypal.api.payments.ItemList;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payer;
//import com.paypal.api.payments.PayerInfo;
//import com.paypal.api.payments.Payment;
//import com.paypal.api.payments.PaymentExecution;
//import com.paypal.api.payments.RedirectUrls;
//import com.paypal.api.payments.ShippingAddress;
//import com.paypal.api.payments.Transaction;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class PaymentServices {
//	private static final String CLIENT_ID = "";
//	private static final String CLIENT_SECRET = "";
//	private String mode = "sandbox";
//
//	private HttpServletRequest request;
//	private HttpServletResponse response;
//
//	public PaymentServices(HttpServletRequest request, HttpServletResponse response) {
//		super();
//		this.request = request;
//		this.response = response;
//	}
//
//	public void authorizePayment(BookOrder order) {
//		Payer payer=getPayerInformation(order);
//		RedirectUrls redirectUrls=getRedirectURLs();
//		Amount amount=getAmountDetails(order);
//		List<Transaction> transactions=getTransactionInformation(order);
//		
//		// request payment
//		Payment requestPayment=new Payment();
//		requestPayment.setPayer(payer);
//		requestPayment.setRedirectUrls(redirectUrls);
//		requestPayment.setIntent("authorize");
//		requestPayment.setTransaction(transactions);
//		
//		System.out.println("====REQUEST PAYMENT: ====");
//		System.out.println(requestPayment);
//		
//		APIContext apiContext=new APIContext(CLIENT_ID,CLIENT_SECRET,mode);
//		try {
//			Payment authorizedPayment=requestPayment.create(apiContext);
//			System.out.println("====AUTHORIZED PAYMENT: ====");
//			System.out.println(authorizedPayment);
//			
//			String approvalURL=getApprovalURL(authorizedPayment);
//			
//			response.sendRedirect(approvalURL);
//		}catch(PayPalRESTException e){
//			e.printStackTrace();
//			throw new ServletException("Error in authorizing payment.");
//		}
//		
//		// get approval link
//		
//		// redirect to Paypal's payment page
//	}
//	
//	private String getApprovalURL(Payment authorizedPayment) {
//		String approvalURL=null;
//		
//		List<Links> links=authorizedPayment.getLinks();
//		
//		for(Links link:links) {
//			if(link.getRel().equalsIgnoreCase("approval_url")) {
//				approvalURL=link.getHref();
//				break;
//			}
//		}
//		
//		return approvalURL;
//	}
//
//	private List<Transaction> getTransactionInformation(BookOrder order) {
//		Transaction transaction=new Transaction();
//		transaction.setDescription("Books ordered on Evergreen Books");
//		Amount amount=getAmountDetails(order);
//		transaction.setAmount(amount);
//		
//		ItemList itemList=new ItemList();
//		ShippingAddress shippingAddress=getRecipientInformation(order);
//		itemList.setShippingAddress(shippingAddress);
//		
//		List<Item> paypalItems=new ArrayList<>();
//		Iterator<OrderDetail> iterator=order.getOrderDetails().iterator();
//		
//		while(iterator.hasNext()) {
//			OrderDetail orderDetail=iterator.next();
//			Book book=orderDetail.getBook();
//			Integer quantity=orderDetail.getQuantity();
//			
//			Item paypalItem=new Item();
//			paypalItem.setCurrency("USD");
//			paypalItem.setName(book.getTitle());
//			paypalItem.setQuantity(String.valueOf(quantity));
//			paypalItem.setPrice(String.format("%.2f",book.getPrice()));
//			paypalItems.add(paypalItem);                    
//		}
//		itemList.setItems(paypalItems);
//		transaction.setItemList(itemList);
//		
//		List<Transaction> listTransaction=new ArrayList<>();
//		listTransaction.add(transaction);
//		return listTransaction;
//	}
//	
//	private ShippingAddress getRecipientInformation(BookOrder order) {
//		ShippingAddress shippingAddress=new ShippingAddress();
//		String recipientName=order.getFirstname()+" "+order.getLastname();
//		shippingAddress.setRecipientName(recipientName);
//		shippingAddress.setPhone(order.getPhone());
//		shippingAddress.setLine1(order.getAddressLine1());
//		shippingAddress.setLine2(order.getAddressLine2());
//		shippingAddress.setCity(order.getCity());
//		shippingAddress.setState(order.getState());
//		shippingAddress.setCountry(order.getCountry());
//		shippingAddress.setPostalCode(order.getZipcode());
//		
//		return shippingAddress;
//	}
//	
//	private Payer getPayerInformation(BookOrder order) {
//		// get payer info
//			Payer payer=new Payer();
//			payer.setPaymentMethod("paypal");
//			
//			Customer customer=order.getCustomer();
//			
//			PayerInfo payerInfo=new PayerInfo();
//			payerInfo.setFirstName(customer.getFirstname());
//			payerInfo.setLastName(customer.getLastname());
//			payerInfo.setEmail(customer.getEmail());
//			payerInfo.setPayerInfo(payerInfo);
//			return payer;
//	}
//	
//	private RedirectUrls getRedirectURLs() {
//		// get redirect URLs
//			String requestURL=request.getRequestURL().toString();
//			String requestURI=request.getRequestURI();
//			String baseURL=requestURL.replace(requestURI, "").concat(request.getContextPath());
//					
//					
//			RedirectUrls redirectUrls=new RedirectUrls();
//			String cancelUrl=baseURL.concat("/view_cart");
//			String returnUrl=baseURL.concat("/review_payment");
//			
//			System.out.println("Return URL: "+returnUrl);
//			System.out.println("Cancel URL: "+cancelUrl);
//			
//			redirectUrls.setCancelUrl(cancelUrl);
//			redirectUrls.setReturnUrl(returnUrl);
//			return redirectUrls;
//	}
//
//	private Amount getAmountDetails(BookOrder order) {
//		// get amount details
//			Details details=new Details();
//			details.setShipping(String.format("%.2f",order.getShippingFee()));
//			details.setTax(String.format("%.2f",order.getTax()));
//			details.setSubtotal(String.format("%.2f",order.getSubtotal()));
//			
//			Amount amount=new Amount();
//			amount.setCurrency("USD");
//			amount.setDetails(details);
//			amount.setTotal(String.format("%.2f",order.getTotal()));
//			return amount;
//	}
//
//	public void reviewPayment() throws ServletException, IOException {
//		String paymentId=request.getParameter("paymentId");
//		String payerId=request.getParameter("PayerID");
//		
//		if(paymentId==null || payerId==null) {
//			throw new ServletException("Error in displaying payment review");
//		}
//		
//		APIContext apiContext=new APIContext(CLIENT_ID,CLIENT_SECRET,mode);
//		try {
//			Payment payment=Payment.get(apiContext, paymentId);
//			
//			PayerInfo payerInfo=payment.getPayer().getPayerInfo();
//			Transaction transaction=payment.getTransactions().get(0);
//			ShippingAddress shippingAddress=transaction.getItemList().getShippingAddress();
//			
//			request.setAttribute("payer", payerInfo);
//			request.setAttribute("recipinet", shippingAddress);
//			request.setAttribute("transaction", transaction);
//			
//			String reviewPage="frontend/review_payment.jsp?paymentId="+paymentId+"&PayerID="+payerId;
//			request.getRequestDispatcher(reviewPage).forward(request, response);
//		}catch(PayPalRESTException e | IOException e) {
//			e.printStackTrace();
//			throw new ServletException("Error in getting payment details from PayPal.");
//		}
//	}
//
//	public Payment executePayment() throws PayPalRESTException {
//		String paymentId=request.getParameter("paymentId");
//		String payerId=request.getParameter("PayerID");
//		
//		PaymentExecution paymentExecution=new PaymentExecution();
//		paymentExecution.setPayerId(payerId);
//		
//		Payment payment=new Payment().setId(payerId);
//		
//		APIContext apiContext=new APIContext(CLIENT_ID,CLIENT_SECRET,mode);
//		
//		return payment.execute(apiContext, paymentExecution);
//	}
//}
