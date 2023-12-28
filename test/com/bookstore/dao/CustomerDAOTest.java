package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Customer;

class CustomerDAOTest {
	private static CustomerDAO customerDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		customerDao=new CustomerDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setEmail("priya@gmail.com");
		customer.setFirstname("Priya");
		customer.setLastname("Priya");
		customer.setCity("Warangal");
		customer.setState("Telangana");
		customer.setCountry("India");
		customer.setAddressLine1("North Side");
		customer.setAddressLine2("North East Side");
		customer.setPassword("priyapassword");
		customer.setPhone("984857");
		customer.setZipcode("0987");
		
		Customer savedCustomer=customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId()>0);
	}

	@Test
	void testGet() {
		Integer customerId=10;
		Customer customer=customerDao.get(customerId);
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer=customerDao.get(10);
		String lastname="Warrior";
		customer.setLastname(lastname);
		
		Customer updatedCustomer=customerDao.update(customer);
		
		assertTrue(updatedCustomer.getLastname().equals(lastname));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId=3;
		customerDao.delete(customerId);
		Customer customer=customerDao.get(customerId);
		
		assertNull(customer);
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers=customerDao.listAll();
		for(Customer customer:listCustomers) {
			System.out.println(customer.getFirstname());
		}
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomers=customerDao.count();
		
		assertEquals(3,totalCustomers);
	}
	
	@Test
	public void testFindByEmail() {
		String email="test@gmail.com";
		Customer customer=customerDao.findByEmail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="test@gmail.com";
		String password="secret";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFailure() {
		String email="abc@gmail.com";
		String password="secret";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNull(customer);
	}
	
}
