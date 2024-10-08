package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Semester;

public interface SemesterService {

	ResponseEntity<String> addSemester(@NonNull Semester semester);

	ResponseEntity<List<Semester>> getAllSemesters();

	ResponseEntity<Semester> getSemester(@NonNull Integer sid);

	ResponseEntity<String> updateSemester(@NonNull Integer sid, String semesterName);

	ResponseEntity<Integer> deleteSemester(@NonNull Integer sid);

}
