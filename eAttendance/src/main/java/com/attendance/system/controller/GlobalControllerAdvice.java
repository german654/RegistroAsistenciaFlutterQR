package com.attendance.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.attendance.system.model.SiteUser;
import com.attendance.system.service.UserService;
import com.attendance.system.service.UtilityService;


@ControllerAdvice
public class GlobalControllerAdvice {
	
	@Autowired
	private UtilityService utility;

	@Autowired
	private UserService userService;
	
	@ModelAttribute("siteUser")
	public SiteUser getName() {
		return userService.getUser(utility.getCurrentUsername()).getBody();
	}
}