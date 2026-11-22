package com.studentmanagmentsystem.dao;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.UncheckedIOException;
import java.io.IOException;
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
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Student findStudentByNationalId(String nationalId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
