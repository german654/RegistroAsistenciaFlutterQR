package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Batch;

public interface BatchDao extends JpaRepository<Batch, Integer>{

}
