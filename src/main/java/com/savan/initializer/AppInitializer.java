package com.savan.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.savan.configuration.SpringConfig;

/**
 * @author SAVAN
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
