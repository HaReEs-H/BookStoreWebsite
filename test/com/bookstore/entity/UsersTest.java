package com.bookstore.entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsersTest {

	public static void main(String[] args) {
		Users user1=new Users();
		user1.setEmail("janu@gmail.com");
		user1.setFullName("Janu");
		user1.setPassword("janu@1234");
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("A users object was persisted");
	}

}
