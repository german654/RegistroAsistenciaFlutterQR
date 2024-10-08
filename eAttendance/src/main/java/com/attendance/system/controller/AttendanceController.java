package com.attendance.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.model.Attendance;
import com.attendance.system.model.QrRequest;
import com.attendance.system.service.AttendanceService;
import com.attendance.system.service.CourseService;
import com.attendance.system.service.SemesterService;
import com.attendance.system.service.StudentService;
import com.attendance.system.service.SubjectService;

@RestController
@RequestMapping("attendance")
public class AttendanceController {


	@Autowired
	private AttendanceService attendanceService;
	

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SemesterService samService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;

	@RequestMapping
	public ModelAndView attendance() {
		ModelAndView mv=new ModelAndView("attendance");
		mv.addObject("courses", courseService.getAllCources().getBody());
		mv.addObject("subjects", subjectService.getAllSubjects().getBody());
		mv.addObject("sams", samService.getAllSemesters().getBody());
		mv.addObject("divisions", studentService.getDivisons().getBody());
		return mv;
	}

	@GetMapping("session/start/{course}/{subject}/{sem}/{div}/{fid}")
	public ResponseEntity<String> startSession(@PathVariable("course") String course_id,@PathVariable("subject") String subject_id,@PathVariable("sem") String sem_id,@PathVariable("div") String division,@PathVariable("fid") String facultyId) {
		return attendanceService.startSession(course_id,subject_id,sem_id,division,facultyId);
	}
	
	@GetMapping("session/stop/{course}/{subject}/{sem}/{div}/{fid}")
	public ResponseEntity<String> stopSession(@PathVariable("course") String course_id,@PathVariable("subject") String subject_id,@PathVariable("sem") String sem_id,@PathVariable("div") String division,@PathVariable("fid") String facultyId) {
		return attendanceService.stopSession(course_id,subject_id,sem_id,division,facultyId);
	}

	@PutMapping("update/{aid}/{isPresent}")
	public ResponseEntity<String> updateAttendance(@PathVariable @NonNull Integer aid,
			@PathVariable Boolean isPresent) {
		return attendanceService.updateAttendance(aid, isPresent);
	}
	
	@GetMapping("get/{aid}")
	public ResponseEntity<Attendance> getAttendance(@PathVariable @NonNull Integer aid){
		return attendanceService.getAttendance(aid);
	}
	
	@GetMapping("fill/{mid}/{sid}")
	public ResponseEntity<Boolean> fillAttendance(@PathVariable Integer mid,@PathVariable @NonNull Long sid,@RequestBody QrRequest request){
		return attendanceService.fillAttendance(mid,sid,request);
	}
	
	
}
