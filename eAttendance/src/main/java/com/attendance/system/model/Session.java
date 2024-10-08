package com.attendance.system.model;

import com.attendance.system.enums.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Session {
    private String sessionId;
    private String course_id;
    private String subject_id;
    private String sem_id;
    private String division;
    private String facultyId;
    private SessionStatus status;

    // Constructors, getters, and setters
}
