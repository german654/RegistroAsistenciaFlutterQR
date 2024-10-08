package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.attendance.system.model.Attendance;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.Student;

public interface AttendanceDao extends JpaRepository<Attendance, Integer>{
	
	@Query("SELECT COUNT(attendanceId) FROM Attendance WHERE studentId = :studentId")
	Integer getTotalAttendance(@Param("studentId") Student student);
	
	@Query("SELECT DISTINCT mapId FROM Attendance WHERE studentId = :student")
	List<Mapping> getAllMappings(@Param("student") Student student);
	
	@Query("SELECT COUNT(attendanceId) FROM Attendance WHERE mapId = :mapping")
	Integer getTotalAttendance(@Param("mapping") Mapping mapping);

}