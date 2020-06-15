package com.dishu.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dishu.shoppingbackend.dao.CategoryDAO;
import com.dishu.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.dishu.shoppingbackend");
		context.refresh();
		categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
		
	}
	
	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Mobile");
		category.setDescription("Some mobile description");
		category.setImageURL("CAT_3.png");
		assertEquals("category added",true,categoryDAO.add(category));
	}*/
	
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(2);
		assertEquals("single category fetched","Laptop",category.getName());
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(2);
		category.setName("Lapy");
		assertEquals("single category updated",true,categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(1);
		assertEquals("single category disabled",true,categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void testListCategory() {
		assertEquals("category list fetched",2,categoryDAO.list().size());
	}*/
	
	@Test
	public void testCRUDCategory() {
		//adding the category
		category = new Category();
		category.setName("Laptop");
		category.setDescription("Some laptop description");
		category.setImageURL("CAT_1.png");
		assertEquals("category added",true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("Some television description");
		category.setImageURL("CAT_2.png");
		assertEquals("category added",true,categoryDAO.add(category));
		
		//fetching and updating
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("single category updated",true,categoryDAO.update(category));
		
		//deleting the category
		
		assertEquals("single category disabled",true,categoryDAO.delete(category));
		
		//fetching the list
		assertEquals("category list fetched",1,categoryDAO.list().size());
	}
}
