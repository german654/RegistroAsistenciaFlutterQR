package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappingWrapper {
	
	private List<Course> courses;
	private List<Subject> subjects;
	private List<Semester> semesters;
	private List<SiteUser> faculties;
	private List<Mapping> mappings;

}
