package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.enums.Role;
import com.attendance.system.model.SiteUser;
import com.attendance.system.service.UserService;

@RestController
@RequestMapping("faculty")
public class FacultyController {

	@Autowired
	private UserService facultyService;

	@RequestMapping
	public ModelAndView faculty() {
		return new ModelAndView("faculty").addObject("facultyList", facultyService.getAllUsers(Role.FACULTY).getBody());
	}
	

	@PostMapping("add")
	public ResponseEntity<String> addFaculty(@RequestParam("enrollment") String enrollment,@RequestParam("email") String email,@RequestParam("userName") String userName,@RequestParam("password") String password) {
		SiteUser faculty=SiteUser.builder().enrollment(enrollment).userName(userName).email(email).password(password).build();
		faculty.setRole(Role.FACULTY);
		facultyService.addUser(faculty);
		return ResponseEntity.ok("Faculty Added Successfully");
	}
	
	@GetMapping("get")
	public ResponseEntity<List<SiteUser>> getAllFaculty(){
		return facultyService.getAllUsers(Role.FACULTY);
	}
	
	@GetMapping("getFacultyById/{id}")
	public ResponseEntity<SiteUser> getFacultyById(@PathVariable Long id) {
		return facultyService.getUser(id);
	}
	

	@PutMapping("updateFacultyById/{id}")
	public ResponseEntity<String> updateFacultyById(@PathVariable Long id, @RequestParam String updFacEnroll,
			@RequestParam String updFacName, @RequestParam(required = false) String updFacPass, @RequestParam String updFacEmail) {
		SiteUser faculty = SiteUser.builder().userId(id).userName(updFacName).email(updFacEmail).enrollment(updFacEnroll).password(updFacPass).build();
		facultyService.updateUser(faculty);
		return ResponseEntity.ok("Faculty Updated Successfully");
		
	}

	@DeleteMapping("deleteFacultyById/{id}")
	public ResponseEntity<Boolean> deleteFacultyById(@PathVariable Long id) {
		return facultyService.deleteUser(id);
	}
}
