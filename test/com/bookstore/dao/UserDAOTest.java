package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.bookstore.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import org.junit.Test;

public class UserDAOTest{

	private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception{
		userDAO = new UserDAO();
	}
	
	@AfterClass
	public static void teardown() throws Exception{
		userDAO.close();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("sharma@gmail.com");
		user1.setFullName("Sharma");
		user1.setPassword("sharma@1234");
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("sravani@gmial.com");
		user.setFullName("Sravani");
		user.setPassword("sravani@1234");
		user=userDAO.update(user);
		String expected="sravani@1234";
		String actual=user.getPassword();
		assertEquals(expected,actual);
	}
	@Test
	public void testGetUsersFound() {
		Integer userId=4;
		Users user=userDAO.get(userId);
		assertNotNull(user);
	}
	@Test
	public void testGetUsersNotFound() {
		Integer userId=7;
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test
	public void testDeleteUsers() {
		Integer userId=1;
		userDAO.delete(userId);
		
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test(expected=PersistenceException.class)
	public void testDeleteNonExistUsers() {
		Integer userId=1;
		userDAO.delete(userId);
	}
	@Test 
	public void testListAll() {
		List<Users> listUsers=userDAO.listAll();
		for(Users user:listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size()>0);
	}
	@Test
	public void testCount() {
		long totalUsers=userDAO.count();
		assertEquals(3,totalUsers);
	}
	@Test
	public void testFindByEmail() {
		String email="jyo1@gmail.com";
		Users user=userDAO.findByEmail(email);
		assertNotNull(user);
	}
	@Test
	public void testCheckLoginSuccess() {
		String email="jyo@gmail.com";
		String password="jyo@1234";
		boolean loginResult=userDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}
	@Test
	public void testCheckLoginFailed() {
		String email="someone";
		String password="someone1234";
		boolean loginResult=userDAO.checkLogin(email, password);
		assertFalse(loginResult);
	}
}