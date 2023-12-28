package com.bookstore.controller.frontend.customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bookstore.service.CustomerServices;

@WebServlet("/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerServices customerServices=new CustomerServices(request, response);
		customerServices.showLogin();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerServices customerServices=new CustomerServices(request, response);
		customerServices.doLogin();
	}

}
