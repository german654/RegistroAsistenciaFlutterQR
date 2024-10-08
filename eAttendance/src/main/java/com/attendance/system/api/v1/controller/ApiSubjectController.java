package com.attendance.system.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.system.model.Subject;
import com.attendance.system.service.SubjectService;

@RestController
@RequestMapping("/api/v1/subjects")
public class ApiSubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<Subject>> getAllSubjects(){
		return subjectService.getAllSubjects();
	}
}
