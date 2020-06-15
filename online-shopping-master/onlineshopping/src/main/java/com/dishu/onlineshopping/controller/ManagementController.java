package com.dishu.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dishu.onlineshopping.util.FileUploadUtility;
import com.dishu.onlineshopping.validator.ProductValidator;
import com.dishu.shoppingbackend.dao.CategoryDAO;
import com.dishu.shoppingbackend.dao.ProductDAO;
import com.dishu.shoppingbackend.dto.Category;
import com.dishu.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		Product nProduct = new Product();
		nProduct.setActive(true);
		nProduct.setSupplierId(1);
		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product added successfully");
			}
			else if (operation.equals("category")) {
				mv.addObject("message", "Category added successfully");
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		Product nProduct = productDAO.get(id);
		mv.addObject("product", nProduct);
		return mv;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {
		
		//custom validation for file uploading, validaion when product is new
		if(mProduct.getId()==0) {
		new ProductValidator().validate(mProduct, results);
		}
		else {
			if(mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		//check for errors
		if (results.hasErrors()) {
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("message", "Product validation failed");
			return "page";
		}

		
		// add a new record
		if(mProduct.getId()==0) {
		productDAO.add(mProduct);
		}
		else {
			//update product
			productDAO.update(mProduct);
		}
		
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDAO.update(product);
		return (isActive)? "Product successfully deactivated with id "+id: "Product successfully activated with id "+id;
	}
	
	//to handle category submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		//adding category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	
	// returning categories for all the request mappings
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	// returning category object for all the request mappings
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}
