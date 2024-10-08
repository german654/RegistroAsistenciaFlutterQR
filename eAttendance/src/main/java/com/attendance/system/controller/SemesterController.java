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

import com.attendance.system.model.Semester;
import com.attendance.system.service.SemesterService;

@RestController
@RequestMapping("semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	@RequestMapping
	public ModelAndView semester() {
		return new ModelAndView("semester").addObject("semesterList", semesterService.getAllSemesters().getBody());
	}

	@PostMapping("add")
	public ResponseEntity<String> addSemester(@NonNull Semester semester) {
		return semesterService.addSemester(semester);
	}

	@GetMapping("get")
	public ResponseEntity<List<Semester>> getAllSemesters() {
		return semesterService.getAllSemesters();
	}

	@GetMapping("get/{sid}")
	public ResponseEntity<Semester> getSemester(@PathVariable @NonNull Integer sid) {
		return semesterService.getSemester(sid);
	}

	@PutMapping("update/{sid}")
	public ResponseEntity<String> updateSemester(@PathVariable @NonNull Integer sid,
			@RequestParam("updSem") String semesterName) {
		return semesterService.updateSemester(sid, semesterName);
	}

	@DeleteMapping("delete/{sid}")
	public ResponseEntity<Integer> deleteSemester(@PathVariable @NonNull Integer sid) {
		return semesterService.deleteSemester(sid);
	}
}
