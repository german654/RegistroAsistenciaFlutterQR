package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.enums.Role;
import com.attendance.system.model.SiteUser;


public interface UserDao extends JpaRepository<SiteUser, Long>{

	public List<SiteUser> findByRole(Role role);
	
	public SiteUser findByUserId(Long userId);
	
	public SiteUser findByEmail(String email);
	
	public void deleteByUserId(Long userId);

	public boolean existsByUserName(String userName);

	
}
