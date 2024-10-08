package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {
	
}
