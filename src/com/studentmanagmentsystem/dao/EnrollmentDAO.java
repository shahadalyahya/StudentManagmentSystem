package com.studentmanagmentsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.studentmanagmentsystem.model.Course;
import com.studentmanagmentsystem.model.Enrollment;

public interface EnrollmentDAO {
	
	boolean addEnrollment(String nationalId , String courseCode);
	
	boolean removeEnrollment(Enrollment enrollment);
	
	List<String> getCourseCodesByStudent(String nationalId);
	
	Set<String> getStudentIdsByCourse(String courseCode);
	
	void deleteEnrollmentsByStudent(String nationalId);
	
	boolean deleteEnrollmentsByCourse(String courseCode);
	
}
