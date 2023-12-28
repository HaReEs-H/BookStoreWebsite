package com.bookstore.controller.frontend;

import jakarta.servlet.Filter;
import java.io.IOException;
import java.util.List;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CommonFilter implements Filter {
	
	private final CategoryDAO categoryDAO;

	public CommonFilter() {
		categoryDAO=new CategoryDAO();
	}
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		String path=httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if(!path.startsWith("/admin/")) {
			List<Category> listCategory=categoryDAO.listAll();
			request.setAttribute("listCategory", listCategory);
		}
		System.out.println("CommonFilter -> doFilter");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
