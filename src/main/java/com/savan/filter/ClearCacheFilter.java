package com.savan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SAVAN
 *
 */
@WebFilter(urlPatterns = {"/userprofile","/adminprofile","/login","/doUpdateUser"})
public class ClearCacheFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Insid Logout filter");
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req =  (HttpServletRequest) request;
		
		//prevent browser back button after session expires
		  res.setHeader("Cache-Control","no-cache");
		  res.setHeader("Cache-Control","no-store");
		  res.setHeader("Pragma","no-cache");
		  res.setDateHeader ("Expires", 0);
		
		chain.doFilter(req, res);
		
	}

}
