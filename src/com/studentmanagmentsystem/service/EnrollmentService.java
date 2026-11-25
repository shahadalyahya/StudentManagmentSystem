package com.studentmanagmentsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.studentmanagmentsystem.dao.EnrollmentDAO;
import com.studentmanagmentsystem.model.Enrollment;

public class EnrollmentService {
	
	private EnrollmentDAO enrollmentdao ;
	
	public EnrollmentService(EnrollmentDAO enrollmentdao) {
		this.enrollmentdao = enrollmentdao ;
	}
	
	public boolean removeEnrollment(Enrollment enrollment){
		
		if(enrollmentdao.removeEnrollment(enrollment)) {
			return true;
		}
		
		throw new IllegalArgumentException("Enrollment not found");
	}
	
	public List<String> getCourseCodesByStudent(String nationalId){
		
		return enrollmentdao.getCourseCodesByStudent(nationalId);
	}
	
	
	public boolean deleteEnrollmentsByCourse(String courseCode) {
		return enrollmentdao.deleteEnrollmentsByCourse(courseCode);
	}
}
