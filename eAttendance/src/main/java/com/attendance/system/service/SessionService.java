package com.attendance.system.service;

public interface SessionService {

	boolean createSession(String course_id, String subject_id, String sem_id, String division, String facultyId);

	boolean stopSession(String course_id, String subject_id, String sem_id, String division, String facultyId);

	boolean isSessionAvailable(String course_id, String subject_id, String sem_id, String division, String facultyId);

}
