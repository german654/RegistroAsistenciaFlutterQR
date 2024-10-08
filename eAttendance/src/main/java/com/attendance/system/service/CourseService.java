package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Course;

public interface CourseService {

	ResponseEntity<String> addCourse(@NonNull Course course);

	ResponseEntity<List<Course>> getAllCources();

	ResponseEntity<String> updateCourse(@NonNull Integer cid, String courseName);

	ResponseEntity<Course> getCourse(@NonNull Integer cid);

	ResponseEntity<Integer> deleteCourse(@NonNull Integer cid);

	
}
