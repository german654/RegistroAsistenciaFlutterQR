package com.attendance.system.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

	
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response,Exception ex) {
		
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return new ModelAndView("error-404").addObject("error", errorMessage);
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return new ModelAndView("error").addObject("error", errorMessage);
	        }
	    }
	    return new ModelAndView("error").addObject("error", errorMessage);
	}
}