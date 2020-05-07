package com.savan.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author SAVAN
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.savan"})
public class SpringConfig implements WebMvcConfigurer {
	
	// Freemarker view resolver configuration
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {

		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		
		return resolver;
	}
	
	//freemarker Template path  configuration
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		
		FreeMarkerConfigurer markerConfigurer = new FreeMarkerConfigurer();
		
		markerConfigurer.setTemplateLoaderPath("/WEB-INF/view/ftl/");
		return markerConfigurer;
	}
	
	//to handle external files for freemarker
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	    registry.addResourceHandler("/image/**").addResourceLocations("/image/");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	
}
