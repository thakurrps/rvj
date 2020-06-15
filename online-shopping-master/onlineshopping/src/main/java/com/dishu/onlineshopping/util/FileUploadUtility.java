package com.dishu.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH="E:\\SPRING_PROJECT\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH="";
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		//make sure directory exist otherwise create it
		if(!new File(ABS_PATH).exists()) {
			//create directory
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create directory
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//to the local directory
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			//to the server
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			
		}
		catch(IOException ex) {
			
		}
		
	}
	

}
