package com.studentmanagmentsystem.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.studentmanagmentsystem.dao.FileStudentDAOImpl;
import com.studentmanagmentsystem.dao.StudentDAOImpl;
import com.studentmanagmentsystem.file.StudentFileDAOImpl;
import com.studentmanagmentsystem.model.Student;
import com.studentmanagmentsystem.service.StudentService;

/**
 * Provides the main entry point and user interface for the Student Management
 * System
 * 
 * Handles user input and displays student information
 */
public class Main {

	static Scanner scanner = new Scanner(System.in);
	static StudentService studentService = new StudentService(new FileStudentDAOImpl());

	public static void main(String[] args) {

		showMainMenu();

	}

	/**
	 * Displays the main menu and handles the user's choices until the user chooses
	 * to exit
	 */
	private static void showMainMenu() {

		int option;

		do {

			showMenu();

			try {

				option = scanner.nextInt();

				scanner.nextLine();

				switch (option) {

				case 1:
					addStudent();
					break;
				case 2:
					showStudent();
					break;
				case 3:
					findStudent();
					break;
				case 4:
					updateStudent();
					break;
				case 5:
					deleteStudent();
					break;
				case 0:
					System.out.println("\nThank you for using our system!");
					break;
				default:
					System.out.println("\nInvalid option. Please try again");

				}

			} catch (InputMismatchException e) {

				scanner.nextLine();
				option = -1;
				System.out.println("Please enter a valid number");

			}

		} while (option != 0);

	}

	private static void showMenu() {

		System.out.println("\n======================================");
		System.out.println("       STUDENT MANAGEMENT SYSTEM");
		System.out.println("======================================");
		System.out.println("Welcome! Manage your student records");
		System.out.println("\n1. Add New Student");
		System.out.println("2. View All Students");
		System.out.println("3. Search for a Student");
		System.out.println("4. Update Student");
		System.out.println("5. Delete Student");
		System.out.println("0. Exit");
		System.out.print("\nEnter your choice: ");

	}

	private static void addStudent() {

		while (true) {

			String nationalId = getValidNationalId();
			System.out.println("Enter student name:");
			String name = scanner.nextLine();

			System.out.println("Enter student age:");
			int age = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Enter student grade:");
			double grade = scanner.nextDouble();
			scanner.nextLine();

			Student student = new Student(nationalId, name, age, grade);

			try {

				studentService.addStudent(student);
				System.out.println("Student added successfully!");

				break;

			} catch (IllegalArgumentException e) {

				System.out.println(e.getMessage());

			}

		}

	}

	private static void showStudent() {

		try {
			List<String> students = studentService.getStudentsFromFile();

			if (students.isEmpty()) {
				System.out.println("No students found");
				return;
			}

			System.out.println("\n--- Student List ---");

			for (String student : students) {
				System.out.println(student);
			}

		} catch (IOException e) {
			System.out.println("Error reading student file: " + e.getMessage());
		}
	}

	private static void findStudent() {

		String nationalId = getValidNationalId();
		Student student = studentService.findStudentByNationalId(nationalId);

		if (student != null) {

			System.out.println(showStudentInfo(student));

		} else {

			System.out.println("Student not found!");

		}

	}

	private static void updateStudent() {

		String nationalId = getValidNationalId();

		Student student = studentService.findStudentByNationalId(nationalId);

		if (student == null) {
			System.out.println("Student not found!");
			return;
		}

		while (true) {

			System.out.print("Enter new name: ");
			String name = scanner.nextLine();

			System.out.println("Enter student age:");
			int age = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Enter student grade:");
			double grade = scanner.nextDouble();
			scanner.nextLine();

			if (studentService.updateStudent(nationalId, name, age, grade)) {

				try {
					studentService.saveStudentsToFile();

					System.out.println("Student updated successfully!");
					System.out.println("Student data saved to file!");

				} catch (IOException e) {
					System.out.println("Error saving student file: " + e.getMessage());
				}

				break;

			} else {
				System.out.println("Invalid student data! Please try again");
			}
		}
	}

	private static void deleteStudent() {

		String nationalId = getValidNationalId();
		Student student = studentService.findStudentByNationalId(nationalId);

		if (student == null) {

			System.out.println("Student not found!");

			return;

		}

		System.out.println("\nStudent Found!!!");
		System.out.println(showStudentInfo(student));
		System.out.println("\nAre you sure you want to delete this student? (Y/N)");

		String confirmation = scanner.nextLine();

		if (confirmation.equalsIgnoreCase("Y")) {

			if (studentService.deleteStudent(nationalId)) {
				System.out.println("Student deleted successfully!");
			}

		} else if (confirmation.equalsIgnoreCase("N")) {

			System.out.println("Delete operation cancelled");

		} else {

			System.out.println("Invalid choice! Please enter Y or N");

		}

	}

	private static String getValidNationalId() {

		System.out.print("Enter National ID: ");
		String nationalId = scanner.nextLine();

		while (true) {
			try {
				return studentService.getValidNationalId(nationalId);

			} catch (IllegalArgumentException e) {

				System.out.println("Invalid National ID!");
				System.out.println(e.getMessage());

				System.out.print("Enter National ID again: ");
				nationalId = scanner.nextLine();
			}
		}
	}

	private static String showStudentInfo(Student student) {

		return "Student information: \n" + "National ID: " + student.getNationalId() + "\nName: " + student.getName()
				+ "\nAge: " + student.getAge() + "\nGrade: " + student.getGrade() + "\nGrade Level: "
				+ studentService.getGradeLevel(student.getGrade()) + "\nStatus: "
				+ studentService.studentStatus(student.getGrade());

	}

}