package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Subject;

public interface SubjectDao extends JpaRepository<Subject,Integer>{

}
