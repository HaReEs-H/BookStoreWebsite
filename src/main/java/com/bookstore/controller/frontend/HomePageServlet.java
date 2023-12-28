package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;


import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomePageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO bookDAO=new BookDAO();
		
		List<Book> listNewBooks=bookDAO.listNewBooks();
		List<Book> listBestSellingBooks=bookDAO.listBestSellingBooks();
		List<Book> listFavoredBooks=bookDAO.listMostFavoredBooks();
		
		
		request.setAttribute("listNewBooks",listNewBooks);
		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
		request.setAttribute("listFavoredBooks", listFavoredBooks);
		
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}
}
