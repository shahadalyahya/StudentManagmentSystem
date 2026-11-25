package com.studentmanagmentsystem.dao;

import java.util.ArrayList;
import java.util.Set;

import com.studentmanagmentsystem.model.Course;
import com.studentmanagmentsystem.model.Enrollment;

public interface EnrollmentDAO {
	
	boolean addEnrollment(String nationalId , String courseCode);
	
	void removeEnrollment(Enrollment enrollment);
	
	ArrayList<Course> getCourseCodesByStudent();
	
	Set<String> getStudentIdsByCourse(String courseCode);
	
	void deleteEnrollmentsByStudent(String nationalId);
	
	void deleteEnrollmentsByCourse(String courseCode);
	
}
