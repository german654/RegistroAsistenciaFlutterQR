package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.model.Course;
import com.attendance.system.service.CourseService;

@RestController
@RequestMapping("course")
public class CourseController {


	@Autowired
	private CourseService courseService;

	@RequestMapping
	public ModelAndView course() {
		return new ModelAndView("course").addObject("courseList", courseService.getAllCources().getBody());
	}

	@PostMapping("add")
	public ResponseEntity<String> addCourse(@NonNull Course course) {
		return courseService.addCourse(course);
	}
	
	@GetMapping("get")
	public ResponseEntity<List<Course>> getAllCources(){
		return courseService.getAllCources();
	}

	@GetMapping("get/{cid}")
	public ResponseEntity<Course> getCourse(@PathVariable @NonNull Integer cid) {
		return courseService.getCourse(cid);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<String> updateCourse(@PathVariable("id") @NonNull Integer cid,
			@RequestParam("updCourse") String courseName) {
		return courseService.updateCourse(cid, courseName);
	}

	@DeleteMapping("delete/{cid}")
	public ResponseEntity<Integer> deleteCourse(@PathVariable @NonNull Integer cid) {
		return courseService.deleteCourse(cid);
	}
}
