package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.MappingWrapper;
import com.attendance.system.model.Semester;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Subject;

public interface MappingService {

	ResponseEntity<MappingWrapper> getAll();

	ResponseEntity<String> addMapping(@NonNull Mapping mapping);

	ResponseEntity<Integer> deleteMapping(@NonNull Integer mid);

	ResponseEntity<Mapping> getMapping(Integer mid);

	ResponseEntity<List<Mapping>> getMappingsFor(Course course);
	
	ResponseEntity<List<Mapping>> getMappings(Course course);
	
	ResponseEntity<List<Mapping>> getMappings(Subject subject);
	
	ResponseEntity<List<Mapping>> getMappings(Semester semester);
	
	ResponseEntity<List<Mapping>> getMappings(SiteUser faculty);
	
	ResponseEntity<List<Mapping>> getMappings(Course course,Semester semester,Subject subject);
	
	ResponseEntity<List<Mapping>> getMappings(Course course,Semester semester,SiteUser faculty);
	
	ResponseEntity<List<Mapping>> getMappings(Course course,Semester semester,Subject subject,SiteUser faculty);
	
	ResponseEntity<List<Course>> getCourses();
	
	ResponseEntity<List<Semester>> getSemerters(Course course);
	
	ResponseEntity<List<Subject>> getSubjects(Semester course);
	
}
