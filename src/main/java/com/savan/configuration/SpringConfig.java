package com.savan.configuration;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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
	
	// Free-marker view resolver configuration
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {

		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		
		return resolver;
	}
	
	//free-marker Template path  configuration
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		
		FreeMarkerConfigurer markerConfigurer = new FreeMarkerConfigurer();
		
		markerConfigurer.setTemplateLoaderPath("/WEB-INF/view/ftl/");
		return markerConfigurer;
	}
	
	//to handle external files for free-marker
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	    registry.addResourceHandler("/image/**").addResourceLocations("/image/");
	}
	
	//Multipart file resolver 
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	//MessageSource to provide internationalized messages
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("errorMessages");
		
		return messageSource;
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
