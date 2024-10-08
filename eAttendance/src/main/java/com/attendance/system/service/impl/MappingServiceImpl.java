package com.attendance.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.MappingDao;
import com.attendance.system.enums.Role;
import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.MappingWrapper;
import com.attendance.system.model.Semester;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Subject;
import com.attendance.system.service.CourseService;
import com.attendance.system.service.MappingService;
import com.attendance.system.service.SemesterService;
import com.attendance.system.service.SubjectService;
import com.attendance.system.service.UserService;

@Service
public class MappingServiceImpl implements MappingService {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private MappingDao mappingDao;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<MappingWrapper> getAll() {
		try {
			MappingWrapper mappingWrapper = new MappingWrapper(courseService.getAllCources().getBody(),
					subjectService.getAllSubjects().getBody(), semesterService.getAllSemesters().getBody(),
					userService.getAllUsers(Role.FACULTY).getBody(), mappingDao.findAll());
			return new ResponseEntity<MappingWrapper>(mappingWrapper, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> addMapping(@NonNull Mapping mapping) {
		try {
			mappingDao.save(mapping);
			return new ResponseEntity<String>("<p class='text-success'>Mapping Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Integer> deleteMapping(@NonNull Integer mid) {
		try {
			mappingDao.deleteById(mid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Mapping> getMapping(@Nullable Integer mid) {
		try {
			return new ResponseEntity<Mapping>(mappingDao.findById(mid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappingsFor(Course course) {
		return ResponseEntity.ok(mappingDao.findByCourse(course));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Course course) {
		return ResponseEntity.ok(mappingDao.findByCourse(course));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Subject subject) {
		return ResponseEntity.ok(mappingDao.findBySubject(subject));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Semester semester) {
		return ResponseEntity.ok(mappingDao.findBySemester(semester));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(SiteUser faculty) {
		return ResponseEntity.ok(mappingDao.findByFaculty(faculty));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Course course, Semester semester, Subject subject) {
		return ResponseEntity.ok(mappingDao.findByCourseAndSemesterAndSubject(course, semester, subject));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Course course, Semester semester, SiteUser faculty) {
		return ResponseEntity.ok(mappingDao.findByCourseAndSemesterAndFaculty(course, semester, faculty));
	}

	@Override
	public ResponseEntity<List<Mapping>> getMappings(Course course, Semester semester, Subject subject,
			SiteUser faculty) {
		return ResponseEntity.ok(mappingDao.findByCourseAndSemesterAndSubjectAndFaculty(course, semester, subject, faculty));
	}

	@Override
	public ResponseEntity<List<Course>> getCourses() {
		return ResponseEntity.ok(mappingDao.findAllCourses());
	}

	@Override
	public ResponseEntity<List<Semester>> getSemerters(Course course) {
	return ResponseEntity.ok(mappingDao.findAllSemesters(course));
	}

	@Override
	public ResponseEntity<List<Subject>> getSubjects(Semester sem) {
		return ResponseEntity.ok(mappingDao.findAllSubjects(sem));
	}
		
}
