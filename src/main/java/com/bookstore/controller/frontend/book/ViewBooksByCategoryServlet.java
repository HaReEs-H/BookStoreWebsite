package com.bookstore.controller.frontend.book;


import com.bookstore.service.BookServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view_category")
public class ViewBooksByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewBooksByCategoryServlet() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("List books by category - Served at: ").append(request.getContextPath());
		BookServices bookServices=new BookServices(request,response);
		bookServices.listBookByCategory();
	}

}
