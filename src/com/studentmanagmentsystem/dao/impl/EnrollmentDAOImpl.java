package com.studentmanagmentsystem.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.studentmanagmentsystem.dao.EnrollmentDAO;
import com.studentmanagmentsystem.model.Enrollment;
import com.studentmanagmentsystem.model.Student;

public class EnrollmentDAOImpl implements  EnrollmentDAO{
	
	private final Set<Enrollment> enrollmentSet ; 
	
	public EnrollmentDAOImpl() {
		this.enrollmentSet = new HashSet<Enrollment>();
	}

	@Override
	public void addEnrollment(String nationalId, String courseCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeEnrollment(Enrollment enrollment) {
		return enrollmentSet.remove(enrollment);
	}

	@Override
	public List<String> getCourseCodesByStudent(String nationalId) {
		List<String> coursesCodes = new ArrayList<>();
		
		for(Enrollment enrollment : enrollmentSet) {
			if(enrollment.getNationalId().equals(nationalId)) {
				coursesCodes.add(enrollment.getCourseCode());
			}
		}
		return coursesCodes;
	}

	@Override
	public ArrayList<Student> getStudentIdsByCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEnrollmentsByStudent(String nationalId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteEnrollmentsByCourse(String courseCode) {
		return enrollmentSet.removeIf(enrollment -> enrollment.getCourseCode().equals(courseCode));
		
	}
	
	
}
