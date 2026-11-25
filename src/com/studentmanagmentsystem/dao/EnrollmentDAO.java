package com.studentmanagmentsystem.dao;

import java.util.ArrayList;

import com.studentmanagmentsystem.model.Course;
import com.studentmanagmentsystem.model.Enrollment;
import com.studentmanagmentsystem.model.Student;

public interface EnrollmentDAO {
	
	void addEnrollment(String nationalId , String courseCode);
	
	void removeEnrollment(Enrollment enrollment);
	
	ArrayList<Course> getCourseCodesByStudent();
	
	ArrayList<Student> getStudentIdsByCourse();
	
	void deleteEnrollmentsByStudent(String nationalId);
	
	void deleteEnrollmentsByCourse(String courseCode);
	
}
