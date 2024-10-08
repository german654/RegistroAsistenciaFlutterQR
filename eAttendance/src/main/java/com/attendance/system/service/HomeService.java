package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Attendance;
import com.attendance.system.model.MappingWrapper;

public interface HomeService {
	
	ResponseEntity<MappingWrapper> getAttendanceData();
	
	ResponseEntity<List<Attendance>> attendance();
	
}
