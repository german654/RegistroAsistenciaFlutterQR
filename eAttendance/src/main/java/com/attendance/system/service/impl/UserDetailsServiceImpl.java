package com.attendance.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.attendance.system.model.AuthUserDetails;
import com.attendance.system.model.SiteUser;
import com.attendance.system.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SiteUser user = userService.getUser(email).getBody();

		if (user == null) {
			throw new UsernameNotFoundException("Invalid Username Or Password");
		}

		return new AuthUserDetails(user);
	}
}
