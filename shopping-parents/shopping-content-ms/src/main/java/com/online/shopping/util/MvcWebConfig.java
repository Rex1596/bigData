package com.online.shopping.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcWebConfig extends WebMvcConfigurerAdapter {

	
	@Autowired
	private FileUploadProperteis fileUploadProperteis;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler(fileUploadProperteis.getStaticAccessPath()).addResourceLocations("file:"+fileUploadProperteis.getUploadFolder()+"/");
	}
	
}
