package com.attendance.system.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.attendance.system.enums.SessionStatus;
import com.attendance.system.model.Session;
import com.attendance.system.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	private Map<String, Session> sessions = new ConcurrentHashMap<>();

	@Override
	public boolean createSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		String sessionId = course_id + "-" + subject_id + "-" + sem_id + "-" + division + "-" + facultyId;
		Session session = new Session(sessionId, course_id, subject_id, sem_id, division, facultyId,
				SessionStatus.STARTED);
		sessions.put(sessionId, session);
		System.out.println("Session Startedfor: " + sessionId);
		return true;
	}

	@Override
	public boolean stopSession(String course_id, String subject_id, String sem_id, String division, String facultyId) {
		String sessionId = course_id + "-" + subject_id + "-" + sem_id + "-" + division + "-" + facultyId;
		sessions.remove(sessionId);
		System.out.println("Session Stopedfor: " + sessionId);
		return true;
	}

	@Override
	public boolean isSessionAvailable(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		return sessions.values().stream()
				.anyMatch(session -> session.getCourse_id().equals(course_id)
						&& session.getSubject_id().equals(subject_id) && session.getSem_id().equals(sem_id)
						&& session.getDivision().equals(division) && session.getFacultyId().equals(facultyId));
	}
}
