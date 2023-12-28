package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CategoryDAOTest{
	
	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		categoryDAO=new CategoryDAO();
	}

	@AfterClass
	public static void teardown() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat=new Category("Romance");
		Category category=categoryDAO.create(newCat);
		assertTrue(category!=null && category.getCategoryId()>0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category newCat=new Category("Travelling");
		newCat.setCategoryId(1);
		Category category = categoryDAO.update(newCat);
		assertEquals(newCat.getName(),category.getName());
	}
	
	@Test
	public void testGetCategory() {
		Integer catId=3;
		Category cat=categoryDAO.get(catId);
		assertNotNull(cat);
	}
	
	@Test
	public void testDeleteCategory() {
		Integer catId=3;
		categoryDAO.delete(catId);
		
		Category cat=categoryDAO.get(catId);
		assertNull(cat);
	}
	
	@Test
	public void testListAllCategory() {
		List<Category> listCategory=categoryDAO.listAll();
		/*To print the category names lambda expression*/
		listCategory.forEach(c->System.out.println(c.getName()+", "));
		assertTrue(listCategory.size()>0);
	}
	
	@Test
	public void testcountAllCategory() {
		long totalCategories=categoryDAO.count();
		assertEquals(totalCategories,2);
	}
	
	@Test
	public void testFindByName() {
		String name="Travelling";
		Category category=categoryDAO.findByName(name);
		assertNotNull(category);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name="Java Core1";
		Category category=categoryDAO.findByName(name);
		assertNull(category);
	}
}
