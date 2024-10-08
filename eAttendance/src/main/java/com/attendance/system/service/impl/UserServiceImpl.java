package com.attendance.system.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.UserDao;
import com.attendance.system.enums.Role;
import com.attendance.system.model.SiteUser;
import com.attendance.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<SiteUser> addUser(SiteUser user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setStatus(true);
		user.setIsLoggedIn(false);
		return ResponseEntity.ok(userDao.save(user));
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<SiteUser> updateUser(SiteUser user) {

		SiteUser dbUser = userDao.findByUserId(user.getUserId());

		if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
			dbUser.setEmail(user.getEmail());
		}

		if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
			dbUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}

		if (Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
			dbUser.setUserName(user.getUserName());
		}

		if (Objects.nonNull(user.getEnrollment()) && !"".equalsIgnoreCase(user.getEnrollment())) {
			dbUser.setEnrollment(user.getEnrollment());
		}

		if (Objects.nonNull(user.getStatus())) {
			dbUser.setStatus(user.getStatus());
		}

		if (Objects.nonNull(user.getIsLoggedIn())) {
			dbUser.setIsLoggedIn(user.getIsLoggedIn());
		}

		return ResponseEntity.ok(userDao.save(dbUser));
	}

	@Override
	public ResponseEntity<List<SiteUser>> getAllUsers() {
		return ResponseEntity.ok(userDao.findAll());
	}

	@Override
	public ResponseEntity<List<SiteUser>> getAllUsers(Role role) {
		return ResponseEntity.ok(userDao.findByRole(role));
	}

	@Override
	public ResponseEntity<SiteUser> getUser(Long userId) {
		return ResponseEntity.ok(userDao.findByUserId(userId));
	}

	@Override
	public ResponseEntity<SiteUser> getUser(String email) {
		return ResponseEntity.ok(userDao.findByEmail(email));
	}

	@Override
	public ResponseEntity<Boolean> updateLoginStatus(String email, Boolean loginStatus) {
		try {
			SiteUser dbUser = userDao.findByEmail(email);
			dbUser.setIsLoggedIn(loginStatus);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteUser(Long userId) {
		try {
			userDao.deleteByUserId(userId);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteUser(@NonNull SiteUser user) {
		try {
			userDao.delete(user);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

}
