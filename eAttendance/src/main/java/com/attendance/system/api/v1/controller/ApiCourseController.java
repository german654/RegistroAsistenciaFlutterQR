package com.attendance.system.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.system.model.Course;
import com.attendance.system.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class ApiCourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<Course>> getAllCourses(){
		return courseService.getAllCources();
	}
	
}
