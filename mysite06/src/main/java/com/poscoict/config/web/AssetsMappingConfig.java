package com.poscoict.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@PropertySource("classpath:com/poscoict/mysite/config/web/assets.properties")
public class AssetsMappingConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
			.addResourceHandler(env.getProperty("assets.assetsMapping"))
			.addResourceLocations("classpath:" + env.getProperty("assets.assetsLocation"));
	}
	
}
