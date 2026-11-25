package com.studentmanagmentsystem.service;

import java.util.Set;

import com.studentmanagmentsystem.dao.CourseDAO;
import com.studentmanagmentsystem.dao.EnrollmentDAO;
import com.studentmanagmentsystem.dao.StudentDAO;

public class EnrollmentService {
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private EnrollmentDAO enrollmentDAO;

	public EnrollmentService(StudentDAO studentDAO, CourseDAO courseDAO, EnrollmentDAO enrollmentDAO) {

		this.studentDAO = studentDAO;
		this.courseDAO = courseDAO;
		this.enrollmentDAO = enrollmentDAO;
	}

	public boolean enrollStudent(String nationalId, String courseCode) {

		if (studentDAO.findStudentByNationalId(nationalId) == null) {
			throw new IllegalArgumentException("Student does not exist");
		}

		if (courseDAO.findCourseByCode(courseCode) == null) {
			throw new IllegalArgumentException("Course does not exist");
		}

		return enrollmentDAO.addEnrollment(nationalId, courseCode);
	}

	public void deleteEnrollmentsByStudent(String nationalId) {
		enrollmentDAO.deleteEnrollmentsByStudent(nationalId);
	}

	public Set<String> getStudentIdsByCourse(String courseCode) {
		return enrollmentDAO.getStudentIdsByCourse(courseCode);
	}

}
