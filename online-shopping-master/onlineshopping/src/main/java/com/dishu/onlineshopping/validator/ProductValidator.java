package com.dishu.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dishu.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;
		// check whether file is selected or not
		// here null=error code
		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file",null,"Please select an image to upload");
			return;
		}
		
		if(! product.getFile().getContentType().equals("image/jpeg")||
			product.getFile().getContentType().equals("image/png")||
			product.getFile().getContentType().equals("image/gif")) {
			errors.rejectValue("file", null,"Please select image file only");
			return;
		}

	}

}
