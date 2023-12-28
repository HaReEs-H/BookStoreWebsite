package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {
		listUser("");
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("ls", listUsers);
		request.setAttribute("message", message);
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullname = request.getParameter("full_name");
		String password = request.getParameter("password");

		// response.getWriter().println("Email: "+email);
		// response.getWriter().println("Fullname: "+fullname);
		// response.getWriter().println("Password: "+password);
		// Checking that email already exists
		Users existUser = userDAO.findByEmail(email);
		if (existUser != null) {
			String message = "Could not create user.A user with email " + email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, password, fullname);
			userDAO.create(newUser);
			listUser("New user created successfully");
		}
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("user_Id"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("full_name");
		String password = request.getParameter("password");
		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user. User with email " + email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Users user = new Users(userId, email, password, fullName);
			userDAO.update(user);
			String message = "User has been updated successfully";
			listUser(message);
		}
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		String message = "User has been deleted successfully";
		if (userId == 1) {
			message = "The default admin user account cannot be deleted.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		userDAO.delete(userId);
		listUser(message);
	}

	public void login() throws ServletException, IOException {
		String email=request.getParameter("email_name");
		String password=request.getParameter("password");
		boolean loginResult=userDAO.checkLogin(email, password);
		if(loginResult) {
			/*System.out.println("User is authenticated")*/
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else {
			/*System.out.println("Login failed")*/
			String message="Login Failed";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
