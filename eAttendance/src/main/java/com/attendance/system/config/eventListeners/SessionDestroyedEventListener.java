package com.attendance.system.config.eventListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.attendance.system.service.UserService;

@Component
public class SessionDestroyedEventListener implements ApplicationListener<SessionDestroyedEvent> {

	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(@NonNull SessionDestroyedEvent event) {
		
		// Handle session expiration event
		event.getSecurityContexts().forEach(securityContext -> {
			String email = securityContext.getAuthentication().getName();

			userService.updateLoginStatus(email, false);

//			System.out.println("Session expired for user: " + email);
		});
	}
}