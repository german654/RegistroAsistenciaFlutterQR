package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceData {
	private List<Subject> subjects;
	private List<Integer> subjectAttendance;
	private Integer total;
}
