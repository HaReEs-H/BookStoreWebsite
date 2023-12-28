package com.bookstore.controller.admin.review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bookstore.service.ReviewServices;

@WebServlet("/admin/list_reviews")
public class ListReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListReviewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewServices reviewServices=new ReviewServices(request, response);
		reviewServices.listAllReview();
	}

}
