package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.Semester;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Subject;

public interface MappingDao extends JpaRepository<Mapping, Integer> {

	List<Mapping> findByCourse(Course course);

	List<Mapping> findByFaculty(SiteUser faculty);

	List<Mapping> findBySemester(Semester semester);

	List<Mapping> findBySubject(Subject subject);

	List<Mapping> findByCourseAndSemesterAndSubject(Course course, Semester semester, Subject subject);

	List<Mapping> findByCourseAndSemesterAndFaculty(Course course, Semester semester, SiteUser faculty);

	List<Mapping> findByCourseAndSemesterAndSubjectAndFaculty(Course course, Semester semester, Subject subject,
			SiteUser faculty);

	@Query("SELECT DISTINCT m.course FROM Mapping m")
	List<Course> findAllCourses();
	
	@Query("SELECT DISTINCT m.semester FROM Mapping m WHERE m.course = :course")
	List<Semester> findAllSemesters(@Param("course") Course course);
	
	@Query("SELECT DISTINCT m.subject FROM Mapping m WHERE m.semester = :sem")
	List<Subject> findAllSubjects(@Param("sem") Semester sem);

}
