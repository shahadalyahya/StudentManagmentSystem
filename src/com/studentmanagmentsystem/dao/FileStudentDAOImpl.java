
package com.studentmanagmentsystem.dao;
import org.apache.logging.log4j.Logger;
import com.studentmanagmentsystem.util.AppLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.UncheckedIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileStudentDAOImpl implements StudentDAO {

	private Path filePath;
	private static final Logger logger = LogManager.getLogger(FileStudentDAOImpl.class);

	public FileStudentDAOImpl() {
		this.filePath = Paths.get("Students.txt");
	}

	@Override
	public void addStudent(Student student) {

		logger.info("Starting to add student");

		String data = student.getNationalId() + "," + student.getName() + "," + student.getAge() + ","
				+ student.getGrade();

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

			writer.write(data);
			writer.newLine();
		
		} catch (IOException e) {
			logger.error("Failed to save student", e);
			throw new UncheckedIOException("Failed to save student", e);
		}
		logger.info("Student added successfully");
	}

	@Override
	public boolean deleteStudent(String nationalId) {

	    logger.info("Starting student deletion. National ID: {}", nationalId);

	    if (Files.notExists(filePath)) {
	        logger.warn("Student file does not exist.");
	        return false;
	    }

				String line;

				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
			}

	        if (!isDeleted) {
	            logger.warn("Student not found. National ID: {}", nationalId);
	            return false;
	        }

			if (!isDeleted) {
				return false;
			}

	        logger.info("Student deleted successfully. National ID: {}", nationalId);
	        return true;

	    } catch (IOException e) {
	        logger.error("Failed to delete student. National ID: {}", nationalId, e);
	        throw new UncheckedIOException("Failed to delete student", e);
	    }
	}
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(filePath)) {

			String line;

			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");

				Student student = new Student(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]));

				students.add(student);
			}

		} catch (IOException e) {
			System.out.println("Error reading students file: " + e.getMessage());
		}

		return students;
	}

	@Override
	public boolean updateStudent(String nationalId, Student updatedStudent) {

		List<Student> students = getAllStudents();

		boolean found = false;

		for (int i = 0; i < students.size(); i++) {

			if (students.get(i).getNationalId().equals(nationalId)) {

				students.set(i, updatedStudent);
				found = true;
				break;
			}
		}

		if (found) {

			try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING)) {

				for (Student student : students) {

					writer.write(student.getNationalId() + "," + student.getName() + "," + student.getAge() + ","
							+ student.getGrade());

					writer.newLine();
				}

			} catch (IOException e) {
				System.out.println("Error updating students file: " + e.getMessage());
				return false;
			}
		}

		return found;
	}

	@Override
	public Student findStudentByNationalId(String nationalId) {
		logger.info("Starting student search by ID");
		if (Files.notExists(filePath)) {
			logger.debug("Students file does not exist");
			return null;
		}

		try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {

			String line;

			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");

				if (data[0].equals(nationalId)) {
					logger.info("Student found successfully");
					return new Student(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]));
				}
			}

		} catch (IOException e) {
			logger.error("Failed to read students file", e);
			throw new UncheckedIOException("Failed to read students", e);
		}
		logger.info("Student not found");
		return null;
	}
}