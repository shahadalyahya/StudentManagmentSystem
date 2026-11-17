package StudentManagmentSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Model.Student;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Student> students = new ArrayList<>();

	public static void main(String[] args) {
		showMainMenu();
	}

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
		String nationalId = getValidNationalId();

		while (findStudentByNationalId(nationalId) != null) {
			System.out.println("This National ID already exists!");
			nationalId = getValidNationalId();
		}

		System.out.println("Enter student name:");
		String name = scanner.nextLine();

		while (name.trim().isEmpty()) {
			System.out.println("Name cannot be empty\nTry again:");
			name = scanner.nextLine();
		}

		System.out.println("Enter student age:");
		while (!scanner.hasNextInt()) {
			System.out.println("Please enter a valid integer:");
			scanner.nextLine();
		}
		int age = scanner.nextInt();
		scanner.nextLine();

		while (age <= 0) {
			System.out.println("Age must be greater than zero\nTry again:");
			while (!scanner.hasNextInt()) {
				System.out.println("Please enter a valid integer:");
				scanner.nextLine();
			}
			age = scanner.nextInt();
			scanner.nextLine();
		}

		System.out.println("Enter student grade:");
		while (!scanner.hasNextDouble()) {
			System.out.println("Please enter a valid number:");
			scanner.nextLine();
		}
		double grade = scanner.nextDouble();
		scanner.nextLine();

		while (grade < 0 || grade > 100) {
			System.out.println("Grade must be between 0 and 100 \nTry again:");
			while (!scanner.hasNextDouble()) {
				System.out.println("Please enter a valid number:");
				scanner.nextLine();
			}
			grade = scanner.nextDouble();
			scanner.nextLine();
		}

		students.add(new Student(nationalId, name, age, grade));
		System.out.println("Student added successfully!");
	}

	private static void showStudent() {
		if (students.isEmpty()) {
			System.out.println("No students found");
			return;
		}

		System.out.println("\n--- Student List ---");
		for (Student std : students) {
			System.out.println(std.showStudentInfo());
			System.out.println("-------------------------");
		}
	}

	private static void findStudent() {
		String nationalId = getValidNationalId();
		Student student = findStudentByNationalId(nationalId);

		if (student != null) {
			System.out.println(student.showStudentInfo());
		} else {
			System.out.println("Student not found!");
		}
	}

	private static void updateStudent() {
		String nationalId = getValidNationalId();
		Student student = findStudentByNationalId(nationalId);

		if (student == null) {
			System.out.println("Student not found!");
			return;
		}

		System.out.print("Enter new name: ");
		String name = scanner.nextLine();

		while (name.trim().isEmpty()) {
			System.out.println("Name cannot be empty!");
			System.out.print("Enter new name: ");
			name = scanner.nextLine();
		}

		System.out.print("Enter new age: ");
		while (!scanner.hasNextInt()) {
			System.out.println("Please enter a valid integer!");
			scanner.nextLine();
			System.out.print("Enter new age: ");
		}
		int age = scanner.nextInt();
		scanner.nextLine();

		while (age <= 0) {
			System.out.println("Age must be greater than zero!");
			System.out.print("Enter new age: ");

			while (!scanner.hasNextInt()) {
				System.out.println("Please enter a valid integer!");
				scanner.nextLine();
				System.out.print("Enter new age: ");
			}
			age = scanner.nextInt();
			scanner.nextLine();
		}

		System.out.print("Enter new grade: ");
		while (!scanner.hasNextDouble()) {
			System.out.println("Please enter a valid number!");
			scanner.nextLine();
			System.out.print("Enter new grade: ");
		}
		double grade = scanner.nextDouble();
		scanner.nextLine();

		while (grade < 0 || grade > 100) {
			System.out.println("Grade must be between 0 and 100!");
			System.out.print("Enter new grade: ");

			while (!scanner.hasNextDouble()) {
				System.out.println("Please enter a valid number!");
				scanner.nextLine();
				System.out.print("Enter new grade: ");
			}
			grade = scanner.nextDouble();
			scanner.nextLine();
		}

		student.setName(name);
		student.setAge(age);
		student.setGrade(grade);

		System.out.println("Student updated successfully!");
	}

	private static void deleteStudent() {
		String nationalId = getValidNationalId();
		Student student = findStudentByNationalId(nationalId);

		if (student == null) {
			System.out.println("Student not found!");
			return;
		}

		System.out.println("\nStudent Found:");
		System.out.println(student.showStudentInfo());

		System.out.println("\nAre you sure you want to delete this student?");
		System.out.println("Press Enter to confirm or any other key to cancel:");

		String confirmation = scanner.nextLine();

		if (confirmation.trim().isEmpty()) {
			students.remove(student);
			System.out.println("Student deleted successfully!");
		} else {
			System.out.println("Delete operation cancelled.");
		}
	}

	private static Student findStudentByNationalId(String id) {
		for (Student student : students) {
			if (student.getNationalId().equals(id)) {
				return student;
			}
		}
		return null;
	}

	private static String getValidNationalId() {
		System.out.print("Enter National ID: ");
		String nationalId = scanner.nextLine();

		while (!nationalId.matches("[12][0-9]{9}")) {
			System.out.println("Invalid National ID!");

			if (nationalId.trim().isEmpty()) {
				System.out.println("National ID cannot be empty");
			} else if (nationalId.length() != 10) {
				System.out.println("National ID must contain exactly 10 digits");
			} else if (!nationalId.matches("[12].*")) {
				System.out.println("National ID must start with 1 or 2");
			} else {
				System.out.println("National ID must contain digits only");
			}

			System.out.print("Enter National ID again: ");
			nationalId = scanner.nextLine();
		}

		return nationalId;
	}
}