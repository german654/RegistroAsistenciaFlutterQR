package com.attendance.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.model.Attendance;
import com.attendance.system.model.MappingWrapper;
import com.attendance.system.service.AttendanceService;
import com.attendance.system.service.HomeService;
import com.attendance.system.service.MappingService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private MappingService mapService;
	
	@Autowired
	private AttendanceService attendanceService;

	@Override
	public ResponseEntity<MappingWrapper> getAttendanceData() {
		return mapService.getAll();
	}
	
	public ResponseEntity<List<Attendance>> attendance(){
		return attendanceService.getAll();
	}

}
