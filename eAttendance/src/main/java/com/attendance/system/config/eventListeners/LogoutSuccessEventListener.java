package com.attendance.system.config.eventListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

import com.attendance.system.service.UserService;


@Component
public class LogoutSuccessEventListener implements ApplicationListener<LogoutSuccessEvent> {

	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(@NonNull LogoutSuccessEvent event) {
		// Handle successful logout event
		String email = event.getAuthentication().getName();

		userService.updateLoginStatus(email, false);

//		System.out.println("User logged out: " + email);
		// Add your custom logic here
	}
}