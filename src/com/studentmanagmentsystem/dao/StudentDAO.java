package com.studentmanagmentsystem.dao;

import java.util.List;

import com.studentmanagmentsystem.model.Student;

/**
 * Defines the data access operations for managing students.
 */
public interface StudentDAO {

	/**
	 * Adds a new student to the data storage
	 *
	 * @param student the student to be added
	 */
	public void addStudent(Student student);

	/**
	 * Deletes a student using their national ID
	 *
	 * @param national ID of the student
	 * @return true if the student was deleted, false if not found
	 */
	public boolean deleteStudent(String nationalId);

	/**
	 * Retrieves all students from the data storage
	 *
	 * @return a list containing all students
	 */
	public List<Student> getAllStudents();

	/**
	 * Searches for a student using their national ID
	 *
	 * @param nationalId the national ID to search for
	 * @return the student if found, otherwise null
	 */
	public Student findStudentByNationalId(String nationalId);

	/**
	 * Updates an existing student's information using their national ID
	 *
	 * @param nationalId the national ID of the student to be updated
	 * @param updatedStudent the student object containing the updated information
	 * @return true if the student was updated successfully, false if not found
	 */
	public boolean updateStudent(String nationalId, Student updatedStudent);

}
