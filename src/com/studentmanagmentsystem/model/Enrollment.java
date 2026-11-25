package com.studentmanagmentsystem.model;

import java.util.Objects;

public class Enrollment {
	
	private String nationalId;
	private String courseCode;
	
	public Enrollment(String nationalId , String courseCode) {
		this.nationalId = nationalId;
		this.courseCode = courseCode;
	}
	
	public String getNationalId() {
		return this.nationalId;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nationalId,courseCode);
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Enrollment)) {
			return false;
		}
		
		Enrollment other = (Enrollment) obj ;
		
		return Objects.equals(nationalId, other.nationalId) && Objects.equals(courseCode, other.courseCode);
		
	}
}
