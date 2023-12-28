//package com.bookstore.controller.frontend.order;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//import com.bookstore.service.OrderServices;
//import com.bookstore.service.PaymentServices;
//import com.paypal.api.payments.PayerInfo;
//import com.paypal.api.payments.Payment;
//import com.paypal.api.payments.Transaction;
//import com.paypal.base.rest.PayPalRESTException;
//
//@WebServlet("/execute_payment")
//public class ExecutePaymentServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public ExecutePaymentServlet() {
//		super();
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		PaymentServices paymentServices=new PaymentServices(request, response);
//		
//		try {
//			Payment payment=paymentServices.executePayment();
//			OrderServices orderServices=new OrderServices(request, response);
//			Integer orderId=orderServices.placeOrderPaypal(payment);
//			
//			//response.getWriter().println("Payment Successful. Order ID: "+orderId);
//			
//			HttpSession session=request.getSession();
//			session.setAttribute("orderId", orderId);
//			
//			PayerInfo payerInfo=payment.getPayer().getPayerInfo();
//			Transaction transaction=payment.getTransactions().get(0);
//			
//			session.setAttribute("payer", payerInfo);
//			session.setAttribute("transaction", transaction);
//			
//			String recieptPage="frontend/payment_recipiet.jsp";;
//			request.getRequestDispatcher(recieptPage).forward(request, response);
//		} catch (PayPalRESTException e) {
//			e.printStackTrace();
//			throw new ServletException("Error in executing payment.");
//		}
//	}
//
//}
