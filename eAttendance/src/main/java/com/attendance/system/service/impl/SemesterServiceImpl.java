package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.SemesterDao;
import com.attendance.system.model.Semester;
import com.attendance.system.service.SemesterService;

@Service
public class SemesterServiceImpl implements SemesterService {

	@Autowired
	private SemesterDao dao;

	@Override
	public ResponseEntity<String> addSemester(@NonNull Semester semester) {
		try {
			dao.save(semester);
			return new ResponseEntity<String>("<p class='text-success'>Semester Added Successfully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p>" + e.getMessage() + "</p>", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Semester>> getAllSemesters() {
		try {
			return new ResponseEntity<List<Semester>>(dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Semester>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Semester> getSemester(@NonNull Integer sid) {
		try {
			return new ResponseEntity<Semester>(dao.findById(sid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<String> updateSemester(@NonNull Integer sid, String semesterName) {
		try {
			Semester sem = new Semester(sid, semesterName);
			Semester dbSem = dao.findById(sid).get();
			if (Objects.nonNull(sem.getSemesterName()) && !"".equalsIgnoreCase(sem.getSemesterName())) {
				dbSem.setSemesterName(sem.getSemesterName());
			}
			dao.save(dbSem);
			return new ResponseEntity<String>("<p class='text-success'>Semester Updated SuccessFully</p>",
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class'text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Integer> deleteSemester(@NonNull Integer sid) {
		try {
			dao.deleteById(sid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
