package com.savan.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author SAVAN
 *
 */
@ControllerAdvice
public class GlobleExceptionHandler {
	
	// Handle 404 error
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		return "redirect:/login";
	}
	
	// Handle 500 error
	@ExceptionHandler(value = RuntimeException.class)
	public String runtimeExceptionHandler() {
		return "error";
	}

 
}
