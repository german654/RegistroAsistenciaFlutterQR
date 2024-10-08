package com.attendance.system.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.attendance.system.service.UtilityService;


@Service
public class UtilityServiceImpl implements UtilityService {

	@Override
	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			}
		}

		// Return a default or handle the case where the username is not available
		return "unknownUser";
	}
}