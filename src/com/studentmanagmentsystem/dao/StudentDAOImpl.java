package com.studentmanagmentsystem.dao;

import java.util.ArrayList;
import java.util.List;
import com.studentmanagmentsystem.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private ArrayList<Student> students = new ArrayList<>();

	@Override
	public void addStudent(Student student) {

		students.add(student);

	}

	@Override
	public boolean deleteStudent(String nationalId) {

		Student student = findStudentByNationalId(nationalId);

		if (student != null) {

			students.remove(student);

			return true;

		}

		return false;

	}

	/**
	 * Returns a copy of the student list to protect the original collection from
	 * external modifications
	 *
	 * @return a copy of the student list
	 */
	@Override
	public List<Student> getAllStudents() {

		return new ArrayList<>(students);

	}

	@Override
	public Student findStudentByNationalId(String nationalId) {

		for (Student student : students) {

			if (student.getNationalId().equals(nationalId)) {

				return student;

			}

		}

		return null;

	}

	@Override
	public boolean updateStudent(String nationalId, Student updatedStudent) {

		    for (int i = 0; i < students.size(); i++) {

		        if (students.get(i).getNationalId().equals(nationalId)) {

		            students.set(i, updatedStudent);
		            return true;
		        }
		    }

		    return false;
		}
	}

