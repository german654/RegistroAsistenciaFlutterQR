package com.attendance.system.config.eventListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.attendance.system.service.UserService;


@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserService userService;
	
    @Override
    public void onApplicationEvent(@NonNull AuthenticationSuccessEvent event) {
        // Handle successful login event
        String email = event.getAuthentication().getName();
        
        userService.updateLoginStatus(email, true);
        
//        System.out.println("User logged in: " + email);
        // Add your custom logic here
    }
}