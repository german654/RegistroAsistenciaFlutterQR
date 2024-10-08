package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWrapper {
	private List<Student> students;
	private List<Batch> batchs;
	private List<Course> courses;
}
