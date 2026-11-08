package model;

public class Student {

	private String name;
	private int age;
	private double grade;
	private String nationalId;

	public Student(String nationalId, String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.SetNatioalId(nationalId);

	}

	public Student() {
	}

	public void SetName(String name) {
		this.name = name;
	}

	public void SetAge(int age) {
		this.age = age;
	}

	public void SetGrade(int grade) {
		this.grade = grade;
	}

	public void SetNatioalId(String nationalId) {
		// 1. Check if empty or null
		if (nationalId == null || nationalId.trim().isEmpty()) {
			throw new IllegalArgumentException("National ID cannot be empty.");
		}
		// 2. Check if it contains numbers only
		if (!nationalId.matches("[0-9]+")) {
			throw new IllegalArgumentException("National ID must contain numbers only");
		}
		// 3. Check if it starts with 1 or 2
		if (!nationalId.startsWith("1") && !nationalId.startsWith("2")) {
			throw new IllegalArgumentException("National ID must start with 1 or 2");
		}
		// 4. Check length
		if (nationalId.length() != 10) {
			throw new IllegalArgumentException("National ID must be exactly 10 digits");
		}

		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGrade() {
		return grade;
	}

	public String getNationalId() {
		return nationalId;
	}

	public String studentStatus(double grade) {
		if (grade >= 60) {
			return "Passed";
		} else {
			return "Failed";
		}
	}

	public String getGradeLevel(double grade) {
		if (grade >= 90) {
			return "Excellent";
		} else if (grade >= 80) {
			return "Very Good";
		} else if (grade > 70) {
			return "Good";
		} else if (grade >= 60) {
			return "Pass";
		} else {
			return "Fail";
		}
	}

	public String showStudentInfo() {
		return "Student information: \nNatonal ID: " + getNationalId() + "\nName: " + getName() + "\nAge: " + getAge()
				+ "\nGrade: " + getGrade() + "\nGrade Level: " + studentStatus(grade) + "\nStatus: "
				+ studentStatus(grade);
	}

	

}
