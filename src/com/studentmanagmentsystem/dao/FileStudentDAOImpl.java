package com.studentmanagmentsystem.dao;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.UncheckedIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import com.studentmanagmentsystem.model.Student;


public class FileStudentDAOImpl implements StudentDAO  {
	
	private Path filePath ; 
	
	public FileStudentDAOImpl() {
		this.filePath = Path.of("Students.txt");
	}
	
	
	@Override
	public void addStudent(Student student) {
		String data = student.getNationalId()+","+
					  student.getName()+","+
					  student.getAge()+","+
					  student.getGrade()+System.lineSeparator();
		
		
		try {
			Files.writeString(filePath, data, StandardOpenOption.CREATE , StandardOpenOption.APPEND);
		}catch(IOException e) {
			throw new UncheckedIOException("Failed to save student", e);
		}
	}


	@Override
	public boolean deleteStudent(String nationalId) {

	    if (Files.notExists(filePath)) {
	        return false;
	    }

	    try {
	        List<String> lines = Files.readAllLines(filePath);

	        boolean isDeleted = lines.removeIf(line -> {
	            String[] data = line.split(",");
	            return data.length == 4 && data[0].equals(nationalId);
	        });

	        if (!isDeleted) {
	            return false;
	        }

	        Files.write(
	            filePath,
	            lines,
	            StandardOpenOption.TRUNCATE_EXISTING,
	            StandardOpenOption.WRITE
	        );

	        return true;

	    } catch (IOException e) {
	        throw new UncheckedIOException("Failed to delete student", e);
	    }
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

			try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE , StandardOpenOption.APPEND)) {

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
		if(Files.notExists(filePath)) {
			return null;
		}
		
		try {
			List<String> lines = Files.readAllLines(filePath);
			
			for(String line : lines) {
				
				String[] data = line.split(",");
				
				if(data[0].equals(nationalId)) {
					return new Student(data[0] , data[1] ,  
							Integer.parseInt(data[2]) , Double.parseDouble(data[3]) );
				}
			}
			
			
		}catch (IOException e) {
	        throw new UncheckedIOException("Failed to read students", e);
	    }
		
		return null;
	}
	
	

}
