package com.dishu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dishu.onlineshopping.exception.ProductNotFoundException;
import com.dishu.shoppingbackend.dao.CategoryDAO;
import com.dishu.shoppingbackend.dao.ProductDAO;
import com.dishu.shoppingbackend.dto.Category;
import com.dishu.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		/* passing the categories */
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping("about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAboutUs",true);
		return mv;
	}
	
	@RequestMapping("contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContactUs",true);
		return mv;
	}
	
	/* show all products */
	@RequestMapping("/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		/* passing the categories */
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	/* show category products */
	@RequestMapping("/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id")int id) {
		ModelAndView mv = new ModelAndView("page");
		Category category=null;
		category = categoryDAO.get(id);
		mv.addObject("title", category.getName());
		/* passing the categories */
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	//show single product
	@RequestMapping("/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		product.setViews(product.getViews()+1);
		
		//update views in db
		productDAO.update(product);
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		return mv;
	}
	

}
