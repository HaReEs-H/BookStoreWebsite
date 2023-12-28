package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {

	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO=new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order=new BookOrder();
		Customer customer=new Customer();
		customer.setCustomerId(10);
		
		order.setCustomer(customer);
		order.setFirstname("Anushka");
		order.setLastname("Sharma");
		order.setPhone("123456");
		order.setAddressLine1("UK");
		order.setAddressLine2("Building near Shop");
		order.setCity("Delhi");
		order.setState("New Delhi");
		order.setCountry("IN");
		order.setZipcode("976");
		order.setStatus("Processing");
		order.setPaymentMethod("paypal");
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		OrderDetail orderDetail=new OrderDetail();
		
		Book book=new Book(6);
		orderDetail.setBook(book);
		orderDetail.setQuantity(10);
		orderDetail.setSubtotal(388.7f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		order.setTax(20.6f);
		order.setShippingFee(10.0f);
		order.setSubtotal(388.7f);
		order.setTotal(419.3f);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId()>0);
	}

	@Test
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId=5;
		BookOrder order=orderDAO.get(orderId);
		order.setAddressLine2("Building near Mall");
		
		orderDAO.update(order);
		
		BookOrder updatedOrder=orderDAO.get(orderId);
		assertEquals(order.getAddressLine2(),updatedOrder.getAddressLine2());
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId=5;
		BookOrder order=orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator=order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail=iterator.next();
			if(orderDetail.getBook().getBookId() == 6) {
				orderDetail.setQuantity(5);
				orderDetail.setSubtotal(194.35f);
			}
		}
		
		orderDAO.update(order);
		
		BookOrder updatedOrder=orderDAO.get(orderId);
		
		iterator=order.getOrderDetails().iterator();
		
		int expectedQuantity=5;
		float expectedSubtotal=194.35f;
		int actualQuantity=0;
		float actualSubtotal=0;
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail=iterator.next();
			if(orderDetail.getBook().getBookId() == 6) {
				actualQuantity=orderDetail.getQuantity();
				actualSubtotal=orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity,actualQuantity);
		assertEquals(expectedSubtotal,actualSubtotal,0.0f);
	}

	@Test
	public void testGet() {
		Integer orderId=5;
		BookOrder order=orderDAO.get(orderId);
		
		System.out.println(order.getFirstname());
		System.out.println(order.getLastname());
		System.out.println(order.getPhone());
		System.out.println(order.getAddressLine1());
		System.out.println(order.getAddressLine2());
		System.out.println(order.getCity());
		System.out.println(order.getState());
		System.out.println(order.getCountry());
		System.out.println(order.getZipcode());
		System.out.println(order.getStatus());
		System.out.println(order.getSubtotal());
		System.out.println(order.getShippingFee());
		System.out.println(order.getTax());
		System.out.println(order.getTotal());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(1,order.getOrderDetails().size());
	}
	
	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId=10;
		Integer customerId=99;
		
		BookOrder order=orderDAO.get(orderId,customerId);
		
		assertNull(order);
	}
	
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId=5;
		Integer customerId=10;
		
		BookOrder order=orderDAO.get(orderId,customerId);
		
		assertNotNull(order);
	}

	@Test
	public void testDeleteObject() {
		int orderId=1;
		orderDAO.delete(orderId);
		
		BookOrder order=orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrders=orderDAO.listAll();
		
		for(BookOrder order:listOrders) {
			System.out.println(order.getOrderId()+" - "+order.getCustomer().getFirstname()
					+" - "+order.getTotal()+" - "+order.getStatus());
			for(OrderDetail detail:order.getOrderDetails()) {
				Book book=detail.getBook();
				int quantity=detail.getQuantity();
				float subtotal=detail.getSubtotal();
				System.out.println("\t"+book.getTitle()+" - "+quantity+" - "+subtotal);
			}
		}
		
		assertTrue(listOrders.size()>0);
	}

	@Test
	public void testCount() {
		long totalOrders=orderDAO.count();
		assertEquals(4,totalOrders);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId=99;
		List<BookOrder> listOrders=orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId=10;
		List<BookOrder> listOrders=orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size()>0);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrder> recentOrders=orderDAO.listMostRecentSales();
		
		assertEquals(3,recentOrders.size());
	}

}
