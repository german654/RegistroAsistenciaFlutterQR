package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.enums.Role;
import com.attendance.system.model.SiteUser;

public interface UserService {
	public ResponseEntity<SiteUser> addUser(SiteUser user);
	
	public ResponseEntity<SiteUser> updateUser(SiteUser user);
	
	public ResponseEntity<List<SiteUser>> getAllUsers();
	
	public ResponseEntity<List<SiteUser>> getAllUsers(Role role);
	
	public ResponseEntity<SiteUser> getUser(Long userId);
	
	public ResponseEntity<SiteUser> getUser(String email);
	
	public ResponseEntity<Boolean> updateLoginStatus(String email,Boolean loginStatus); 
	
	public ResponseEntity<Boolean> deleteUser(Long userId);
	
	public ResponseEntity<Boolean> deleteUser(@NonNull SiteUser user);

}
