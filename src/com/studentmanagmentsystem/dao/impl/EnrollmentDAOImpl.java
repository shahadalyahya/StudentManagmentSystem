package com.studentmanagmentsystem.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.studentmanagmentsystem.dao.EnrollmentDAO;
import com.studentmanagmentsystem.model.Course;
import com.studentmanagmentsystem.model.Enrollment;

public class EnrollmentDAOImpl implements EnrollmentDAO {

	private Set<Enrollment> enrollments = new HashSet<>();

	@Override
	public boolean addEnrollment(String nationalId, String courseCode) {
		return enrollments.add(new Enrollment(nationalId, courseCode));
	}

	@Override
	public Set<String> getStudentIdsByCourse(String courseCode) {

		Set<String> studentIds = new HashSet<>();

		for (Enrollment enrollment : enrollments) {
			if (enrollment.getCourseCode().equals(courseCode)) {
				studentIds.add(enrollment.getNationalId());
			}
		}

		return studentIds;
	}

	@Override
	public void deleteEnrollmentsByStudent(String nationalId) {
		enrollments.removeIf(enrollment -> enrollment.getNationalId().equals(nationalId));
	}

	@Override
	public void removeEnrollment(Enrollment enrollment) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Course> getCourseCodesByStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEnrollmentsByCourse(String courseCode) {
		// TODO Auto-generated method stub

	}
}
