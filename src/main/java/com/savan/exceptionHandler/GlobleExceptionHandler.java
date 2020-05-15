package com.savan.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author SAVAN
 *
 */
@ControllerAdvice
public class GlobleExceptionHandler {
	
	// Handle 500 error
	@ExceptionHandler(value = RuntimeException.class)
	public String runtimeExceptionHandler() {
		return "error";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handle(Exception ex) {
       return "redirect:/";
   }
}
