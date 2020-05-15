package com.savan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.savan.model.User;
import com.savan.service.UserService;

/**
 * @author SAVAN
 *
 */
/*
 * @Component
 * 
 * @Order(1)
 */
@WebFilter(urlPatterns = "/afterLogin")
public class LoginFilter implements Filter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		String userName =  request.getParameter("userName");
		String password =  request.getParameter("password");
		
		if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
			
			User u = userService.getUser(userName, password);
			
			if (u != null) {
				request.setAttribute("userId", u.getId());
				request.setAttribute("userRole", u.getRole().getRole());
				chain.doFilter(request, response);
			} else {
				request.setAttribute("loginErr", "*Invalid credentials");
				
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
			}
		}
		else {
			request.setAttribute("loginErr", "*Invalid credentials");

			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
		}
	}
}

