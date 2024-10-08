package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.SubjectDao;
import com.attendance.system.model.Subject;
import com.attendance.system.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao dao;

	@Override
	public ResponseEntity<String> addSubject(@NonNull Subject subject) {
		try {
			dao.save(subject);
			return new ResponseEntity<String>("<p class='text-success'>SubjectAdded SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Subject> getSubject(@NonNull Integer sid) {
		try {
			return new ResponseEntity<Subject>(dao.findById(sid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<Subject>> getAllSubjects() {
		try {
			return new ResponseEntity<List<Subject>>(dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Subject>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<String> updateSubject(@NonNull Integer sid, String subjectName) {
		try {
			Subject subject = new Subject(sid, subjectName);
			Subject dbSubject = dao.findById(sid).get();
			if (Objects.nonNull(subject.getSubjectName()) && !"".equalsIgnoreCase(subject.getSubjectName())) {
				dbSubject.setSubjectName(subject.getSubjectName());
			}
			dao.save(dbSubject);
			return new ResponseEntity<String>("<p class='text-success'>SubjectUpdated Successfully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Integer> deleteSubject(@NonNull Integer sid) {
		try {
			dao.deleteById(sid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
