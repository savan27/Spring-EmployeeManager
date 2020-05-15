package com.savan.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.savan.configuration.SpringConfig;

/**
 * @author SAVAN
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(SpringConfig.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
		new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appContext);

		servletContext.addListener(contextLoaderListener);

		// Filter.
		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/afterLogin");
		
		super.onStartup(servletContext);
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class [] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class [] {SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}
}
