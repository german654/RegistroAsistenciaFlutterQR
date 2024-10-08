package com.attendance.system.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.system.model.Semester;
import com.attendance.system.service.SemesterService;

@RestController
@RequestMapping("/api/v1/semester")
public class ApiSemesterController {

	@Autowired
	private SemesterService semesterService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<Semester>> getAllSemesters(){
		return semesterService.getAllSemesters();
	}
}
