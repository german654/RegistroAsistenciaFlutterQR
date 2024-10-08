package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Subject;

public interface SubjectService {

	ResponseEntity<String> addSubject(@NonNull Subject subject);

	ResponseEntity<Subject> getSubject(@NonNull Integer sid);

	ResponseEntity<List<Subject>> getAllSubjects();

	ResponseEntity<String> updateSubject(@NonNull Integer sid, String subjectName);

	ResponseEntity<Integer> deleteSubject(@NonNull Integer sid);

}
