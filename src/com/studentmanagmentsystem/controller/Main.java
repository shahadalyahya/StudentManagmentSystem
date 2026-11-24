package com.studentmanagmentsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.studentmanagmentsystem.dao.impl.CourseDAOImpl;
import com.studentmanagmentsystem.dao.impl.FileStudentDAOImpl;
import com.studentmanagmentsystem.model.Course;
import com.studentmanagmentsystem.model.Student;
import com.studentmanagmentsystem.service.CourseService;
import com.studentmanagmentsystem.service.StudentService;

/**
 * Provides the main entry point and user interface for the Student Management
 * System
 * 
 * Handles user input and displays student information
 */
public class Main {

	static Scanner scanner = new Scanner(System.in);
	private static final Logger logger = LogManager.getLogger(Main.class);
	static StudentService studentService = new StudentService(new FileStudentDAOImpl());
	static CourseService courseService =new CourseService(new CourseDAOImpl());

	public static void main(String[] args) {

		showMainMenu();

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

		List<Student> students = studentService.getAllStudents();
		if (students.isEmpty()) {

			System.out.println("No students found");

			return;

		}

		System.out.println("\n--- Student List ---");
		for (Student std : students) {

			System.out.println(showStudentInfo(std));
			System.out.println("-------------------------");

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

				System.out.println("Student updated successfully!");

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
	private static void addCourse() {

	    System.out.println("\n===== Add New Course =====");

	    try {
	        System.out.print("Enter Course Code: ");
	        String courseCode = scanner.nextLine();

	        System.out.print("Enter Course Name: ");
	        String courseName = scanner.nextLine();

	        Course course = new Course(courseCode, courseName);

	        courseService.addCourse(course);

	        logger.info("Course added successfully. Course Code: {}", courseCode);

	        System.out.println("\nCourse added successfully!");

	    } catch (IllegalArgumentException e) {

	        logger.warn("Failed to add course: {}", e.getMessage());

	        System.out.println("Error: " + e.getMessage());
	    }
	}
	private static void findCourse() {

	    System.out.println("\n===== Search for a Course =====");

	    try {
	        System.out.print("Enter Course Code: ");
	        String courseCode = scanner.nextLine();

	        Course course = courseService.findCourseByCode(courseCode);

	        if (course != null) {

	            logger.info("Course found. Course Code: {}", courseCode);

	            showCourseInfo(course);

	        } else {

	            logger.warn("Course not found. Course Code: {}", courseCode);

	            System.out.println("\nCourse not found.");
	        }

	    } catch (IllegalArgumentException e) {

	        logger.warn("Invalid course search input: {}", e.getMessage());

	        System.out.println("Error: " + e.getMessage());
	    }
	}
	private static void showCourses() {

	    System.out.println("\n===== All Courses =====");

	    List<Course> courses = courseService.getAllCourses();

	    if (courses.isEmpty()) {

	        logger.info("No courses found.");

	        System.out.println("No courses available.");

	        return;
	    }

	    logger.info("Displaying {} courses.", courses.size());

	    for (Course course : courses) {
	        showCourseInfo(course);
	    }
	}
	
	private static void showCourseInfo(Course course) {

	    System.out.println("----------------------------");
	    System.out.println("Course Code : " + course.getCourseCode());
	    System.out.println("Course Name : " + course.getCourseName());
	    System.out.println("----------------------------");
	}
	
	private static void showMainMenu() {

	    boolean running = true;

	    logger.info("Application started");

	    while (running) {

	        System.out.println("\n===== Main Menu =====");
	        System.out.println("1. Student Management");
	        System.out.println("2. Course Management");
	        System.out.println("0. Exit");
	        System.out.print("Enter your choice: ");

	        try {
	            int option = scanner.nextInt();
	            scanner.nextLine();

	            logger.debug("Main menu option selected: {}", option);

	            switch (option) {

	                case 1:
	                    logger.info("Opening Student Management menu");
	                    showStudentMenu();
	                    break;

	                case 2:
	                    logger.info("Opening Course Management menu");
	                    showCourseMenu();
	                    break;

	                case 0:
	                    running = false;
	                    logger.info("Application exited by user");
	                    System.out.println("Exiting the system...");
	                    break;

	                default:
	                    logger.warn("Invalid main menu option: {}", option);
	                    System.out.println("Invalid option");
	            }

	        } catch (InputMismatchException e) {

	            logger.warn("Invalid input in Main Menu. A number was expected");

	            System.out.println("Please enter a valid number");
	            scanner.nextLine();
	        }
	    }
	}
	private static void showStudentMenu() {

	    int option;

	    logger.info("Student Management menu opened");

	    do {

	        showMenu();

	        try {
	            option = scanner.nextInt();
	            scanner.nextLine();

	            logger.debug("Student menu option selected: {}", option);

	            switch (option) {

	                case 1:
	                    logger.info("User selected Add Student");
	                    addStudent();
	                    break;

	                case 2:
	                    logger.info("User selected View All Students");
	                    showStudent();
	                    break;

	                case 3:
	                    logger.info("User selected Search Student");
	                    findStudent();
	                    break;

	                case 4:
	                    logger.info("User selected Update Student");
	                    updateStudent();
	                    break;

	                case 5:
	                    logger.info("User selected Delete Student");
	                    deleteStudent();
	                    break;

	                case 0:
	                    logger.info("User returned to Main Menu from Student Management");
	                    System.out.println("\nReturning to Main Menu...");
	                    break;

	                default:
	                    logger.warn("Invalid Student menu option: {}", option);
	                    System.out.println("\nInvalid option. Please try again");
	            }

	        } catch (InputMismatchException e) {

	            logger.warn("Invalid input in Student Management menu");

	            scanner.nextLine();
	            option = -1;

	            System.out.println("Please enter a valid number");
	        }

	    } while (option != 0);
	}
	private static void showCourseMenu() {

	    int option;

	    logger.info("Course Management menu opened");

	    do {

	        System.out.println("\n===== Course Management =====");
	        System.out.println("1. Add New Course");
	        System.out.println("2. View All Courses");
	        System.out.println("3. Search for a Course");
	        System.out.println("4. Update Course");
	        System.out.println("5. Delete Course");
	        System.out.println("0. Back to Main Menu");
	        System.out.print("Enter your choice: ");

	        try {
	            option = scanner.nextInt();
	            scanner.nextLine();

	            logger.debug("Course menu option selected: {}", option);

	            switch (option) {

	                case 1:
	                    logger.info("User selected Add Course");
	                    addCourse();
	                    break;

	                case 2:
	                    logger.info("User selected View All Courses");
	                    showCourses();
	                    break;

	                case 3:
	                    logger.info("User selected Search Course");
	                    findCourse();
	                    break;

	                case 0:
	                    logger.info("User returned to Main Menu from Course Management");
	                    System.out.println("\nReturning to Main Menu...");
	                    break;

	                default:
	                    logger.warn("Invalid Course menu option: {}", option);
	                    System.out.println("Invalid option");
	            }

	        } catch (InputMismatchException e) {

	            logger.warn("Invalid input in Course Management menu");

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

}