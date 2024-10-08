package com.attendance.system.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QrRequest {
	private String course;
	private String subject;
	private String sem;
	private String dividion;
	private String duration;
	private String faculty;
	private String createdAt;
}
