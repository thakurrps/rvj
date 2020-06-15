package com.dishu.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dishu.shoppingbackend.dao.ProductDAO;
import com.dishu.shoppingbackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.dishu.shoppingbackend");
		context.refresh();
		productDAO=(ProductDAO)context.getBean("productDAO");
		
	}
	
	/*@Test
	public void testCRUDCategory() {
		//adding the category
		product = new Product();
		product.setName("Oppo selfie s53");
		product.setBrand("Oppo");
		product.setDescription("Some mobile description about oppo selfie s53");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while adding products",true,productDAO.add(product));
		
		
		//fetching and updating
		product = productDAO.get(2);
		product.setName("Samsung galaxy s7");
		assertEquals("Something went wrong while updating the data",true,productDAO.update(product));
		
		//deleting the category
		
		assertEquals("Something went wrong while disabling",true,productDAO.delete(product));
		
		//fetching the list
		assertEquals("Something went wrong while fetching the list",6,productDAO.list().size());
	}*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list",5,productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list",3,productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list",2,productDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testgetLatestActiveProducts() {
		assertEquals("Something went wrong while fetching the list",3,productDAO.getLatestActiveProducts(3).size());
		
	}


}
