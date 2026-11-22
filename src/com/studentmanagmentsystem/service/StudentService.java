package com.studentmanagmentsystem.service;

import java.util.List;
import com.studentmanagmentsystem.dao.StudentDAO;
import com.studentmanagmentsystem.model.Student;

public class StudentService {

	private StudentDAO studentDAO;

	/**
	 * Creates a StudentService with the specified data access object
	 * 
	 * @param studentDAO the data access implementation used to manage students
	 */
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	/**
	 * Validates student information and adds the student if all data is valid and
	 * the national ID is unique
	 *
	 * @param student the student to be added
	 */
	public void addStudent(Student student) {

		getValidNationalId(student.getNationalId());

		if (studentDAO.findStudentByNationalId(student.getNationalId()) != null) {

			throw new IllegalArgumentException("This National ID already exists!");

		}

		if (student.getName() == null || student.getName().trim().isEmpty()) {

			throw new IllegalArgumentException("Name cannot be empty");

		}

		if (student.getAge() <= 0) {

			throw new IllegalArgumentException("Age must be greater than zero");

		}

		if (student.getGrade() < 0 || student.getGrade() > 100) {
			throw new IllegalArgumentException("Grade must be between 0 and 100");

		}

		studentDAO.addStudent(student);

	}

	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();

	}

	public Student findStudentByNationalId(String nationalId) {

		return studentDAO.findStudentByNationalId(nationalId);

	}

	public boolean updateStudent(String nationalId, String name, int age, double grade) {

		Student student = studentDAO.findStudentByNationalId(nationalId);

		if (student == null) {

			return false;

		}

		if (name == null || name.trim().isEmpty()) {

			return false;

		}

		if (age <= 0) {

			return false;

		}

		if (grade < 0 || grade > 100) {

			return false;

		}

		student.setName(name);
		student.setAge(age);
		student.setGrade(grade);

		return studentDAO.updateStudent(nationalId, student);

	}

	public boolean deleteStudent(String nationalId) {

		return studentDAO.deleteStudent(nationalId);

	}

	public String studentStatus(double grade) {

		return grade >= 60 ? "Passed" : "Failed";

	}

	public String getGradeLevel(double grade) {

		if (grade >= 90) {

			return "Excellent";

		} else if (grade >= 80) {

			return "Very Good";

		} else if (grade >= 70) {

			return "Good";

		} else if (grade >= 60) {

			return "Pass";

		} else {

			return "Fail";

		}

	}

	public String getValidNationalId(String nationalId) {

		if (nationalId == null || nationalId.trim().isEmpty()) {
			throw new IllegalArgumentException("National ID cannot be empty");
		}

		if (nationalId.length() != 10) {
			throw new IllegalArgumentException("National ID must contain exactly 10 digits");
		}

		if (!nationalId.matches("[12][0-9]{9}")) {

			if (!nationalId.matches("[12].*")) {
				throw new IllegalArgumentException("National ID must start with 1 or 2");
			}

			throw new IllegalArgumentException("National ID must contain digits only");
		}

		return nationalId;
	}

}
