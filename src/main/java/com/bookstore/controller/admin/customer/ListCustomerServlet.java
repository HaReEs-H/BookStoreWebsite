package com.bookstore.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bookstore.service.CustomerServices;

@WebServlet("/admin/list_customers")
public class ListCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerServices customerServices=new CustomerServices(request,response);
		customerServices.listCustomers();
	}

}
