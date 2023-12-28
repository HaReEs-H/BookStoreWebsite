package com.bookstore.controller.admin.review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bookstore.service.ReviewServices;

@WebServlet("/admin/edit_review")
public class EdtiReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public EdtiReviewServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewServices reviewServices=new ReviewServices(request, response);
		reviewServices.editReview();
	}

}
