package com.studentmanagmentsystem.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.studentmanagmentsystem.model.Student;

public class StudentFileDAOImpl implements StudentFileDAO {

	private static final String FILE_NAME = "students.txt";

	// Check if file exists, otherwise create it
	private void createFileIfNotExists() throws IOException {

		File file = new File(FILE_NAME);

		if (!file.exists()) {
			file.createNewFile();
			System.out.println("Student file created successfully!");
		}
	}

	@Override
	public void saveStudents(List<Student> students) throws IOException {

		createFileIfNotExists();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

			for (Student student : students) {

				writer.write(student.getNationalId() + "," + student.getName() + "," + student.getAge() + ","
						+ student.getGrade());

				writer.newLine();
			}
		}
	}

	@Override
	public List<String> readStudents() throws IOException {

		createFileIfNotExists();

		List<String> students = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

			String line;

			while ((line = reader.readLine()) != null) {
				students.add(line);
			}
		}

		return students;
	}
}
