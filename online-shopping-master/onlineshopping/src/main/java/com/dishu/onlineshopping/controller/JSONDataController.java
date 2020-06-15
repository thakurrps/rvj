package com.dishu.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dishu.shoppingbackend.dao.ProductDAO;
import com.dishu.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JSONDataController {
	
	@Autowired
	ProductDAO productDAO;
	
	
	@RequestMapping("/all/products")
	@ResponseBody                          //this will give data in JSON format
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody                          //this will give data in JSON format
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody                          //this will give data in JSON format
	public List<Product> getCategoryProducts(@PathVariable int id){
		return productDAO.listActiveProductsByCategory(id);
	}
	
	

}
