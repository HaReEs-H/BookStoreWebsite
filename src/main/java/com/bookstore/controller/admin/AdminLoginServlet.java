package com.bookstore.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.bookstore.service.UserServices;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    /*
		    String email=request.getParameter("email_name");
			String password=request.getParameter("password");
			response.getWriter().println(email+" ,"+password);
		   */	
		UserServices userServices=new UserServices(request,response);
		userServices.login();
	}

}
