package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Semester;

public interface SemesterDao extends JpaRepository<Semester, Integer>{

}
