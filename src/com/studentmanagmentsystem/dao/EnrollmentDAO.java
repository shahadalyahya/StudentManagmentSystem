package com.studentmanagmentsystem.dao;

import java.util.ArrayList;
import java.util.List;
import com.studentmanagmentsystem.model.Enrollment;
import com.studentmanagmentsystem.model.Student;

public interface EnrollmentDAO {
	
	void addEnrollment(String nationalId , String courseCode);
	
	boolean removeEnrollment(Enrollment enrollment);
	
	List<String> getCourseCodesByStudent(String nationalId);
	
	ArrayList<Student> getStudentIdsByCourse();
	
	void deleteEnrollmentsByStudent(String nationalId);
	
	boolean deleteEnrollmentsByCourse(String courseCode);
	
}
