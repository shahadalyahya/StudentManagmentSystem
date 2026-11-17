package Model;

public class Student {

	private String name;
	private int age;
	private double grade;
	private String nationalId;

	public Student( String nationalId, String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.nationalId = nationalId;
	}

	public Student() {
	}

	public void SetName(String name) {
		this.name = name;
	}

	public void SetAge(int age) {
		this.age = age;
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


	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
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

		return "Student information: \n" + "National ID: " + getNationalId() + "\nName: " + getName() + "\nAge: "
				+ getAge() + "\nGrade: " + getGrade() + "\nGrade Level: " + getGradeLevel(grade) + "\nStatus: "
				+ studentStatus(grade);
	}

}
