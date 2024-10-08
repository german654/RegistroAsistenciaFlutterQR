package com.attendance.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mapId;
	@ManyToOne
	private Course course;
	@ManyToOne
	private Subject subject;
	@ManyToOne
	private Semester semester;
	@ManyToOne
	private SiteUser faculty;
}
