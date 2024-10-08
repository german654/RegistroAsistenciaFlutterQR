package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;



public interface StudentDao extends JpaRepository<Student, Long>{

	@Query(value="SELECT DISTINCT student_division FROM student",nativeQuery=true)

	List<String> getDivisons();
	
	Student findByUser(SiteUser user);

}
