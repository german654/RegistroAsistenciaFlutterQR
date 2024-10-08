package com.attendance.system.service.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.AttendanceDao;
import com.attendance.system.model.Attendance;
import com.attendance.system.model.AttendanceData;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.QrRequest;
import com.attendance.system.model.Student;
import com.attendance.system.model.Subject;
import com.attendance.system.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private MappingServiceImpl mappingService;

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private SessionServiceImpl sessionService;

	@Override
	public ResponseEntity<String> addAttendance(@NonNull Attendance attendance) {
		try {
			attendanceDao.save(attendance);
			return new ResponseEntity<String>("Attendance Marked SuccessFully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> updateAttendance(@NonNull Integer aid, Boolean isPresent) {
		try {
			Attendance dbAttendance = attendanceDao.findById(aid).get();
			dbAttendance.setIsPresent(isPresent);
			return new ResponseEntity<String>("Attendance Updated SuccessFully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Server Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Attendance> getAttendance(@NonNull Integer aid) {
		try {
			return new ResponseEntity<Attendance>(attendanceDao.findById(aid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> startSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		try {
			if (sessionService.createSession(course_id, subject_id, sem_id, division, facultyId)) {
				return new ResponseEntity<String>("Session Started SuccessFully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Could Not Start Session", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Could Not Start Session: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> stopSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		try {
			if (sessionService.stopSession(course_id, subject_id, sem_id, division, facultyId)) {
				System.out.print("Session Stopped");
				return new ResponseEntity<String>("Session Stopped SuccessFully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("There is No Session To Stop", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Could Not Stop Session", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Boolean> fillAttendance(Integer mid, @NonNull Long sid, QrRequest request) {
		try {

			LocalTime currentTime = LocalTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

			LocalTime now = LocalTime.parse(currentTime.format(formatter), formatter);
			LocalTime createdAt = LocalTime.parse(request.getCreatedAt(), formatter);

			long minutesDifference = Math.abs(now.until(createdAt, java.time.temporal.ChronoUnit.MINUTES));

			if (Long.parseLong(request.getDuration()) < minutesDifference) {
				throw new Exception("Qr Expired, Please Try Again");
			}

			Mapping mapping = mappingService.getMapping(mid).getBody();
			Student student = studentService.getStudent(sid).getBody();
			System.err.println("filling Attendance");
			if (request != null && student != null && sessionService.isSessionAvailable(request.getCourse(),
					request.getSubject(), request.getSem(), request.getDividion(), request.getFaculty())) {
				System.err.println("session is active");
				Attendance attendance = new Attendance();
				attendance.setDateTime(new Date());
				attendance.setIsPresent(true);
				attendance.setMapId(mapping);
				attendance.setStudentId(student);
				attendanceDao.save(attendance);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				System.err.println("session is not Active");
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Attendance>> getAll() {
		return ResponseEntity.ok(attendanceDao.findAll());
	}

	@Override
	public ResponseEntity<AttendanceData> getAttendanceData(Student student) {
		AttendanceData data = new AttendanceData();
		
		data.setTotal(attendanceDao.getTotalAttendance(student));
		
		List<Mapping> mappings = attendanceDao.getAllMappings(student);
		List<Subject> subjects = new ArrayList<>();
		List<Integer> subjectAtt = new ArrayList<>();
		for (Mapping map : mappings) {
			subjects.add(map.getSubject());
			subjectAtt.add(attendanceDao.getTotalAttendance(map));
		}
		data.setSubjects(subjects);
		data.setSubjectAttendance(subjectAtt);

		return ResponseEntity.ok(data);
	}
}
