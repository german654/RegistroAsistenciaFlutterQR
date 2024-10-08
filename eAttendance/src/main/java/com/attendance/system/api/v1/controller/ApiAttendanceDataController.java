package com.attendance.system.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.system.model.AttendanceData;
import com.attendance.system.model.Student;
import com.attendance.system.service.AttendanceService;


@RequestMapping("/api/v1/attendance")
@RestController
public class ApiAttendanceDataController {

	@Autowired
	private AttendanceService attendanceService;
	
	@GetMapping("data/{student}")
	public ResponseEntity<AttendanceData> attendanceData(@PathVariable Student student){
		return attendanceService.getAttendanceData(student);
	}
}
