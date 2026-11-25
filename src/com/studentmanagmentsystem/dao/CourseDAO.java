package com.studentmanagmentsystem.dao;

import java.util.List;
import com.studentmanagmentsystem.model.Course;

public interface CourseDAO {

    /**
     * Adds a new course
     * @param course the course to be added
     */
	void addCourse(Course course);

    /**
     * Finds a course using its unique course code
     *
     * @param courseCode the unique code of the course
     * @return the matching course, or null if not found
     */
	Course findCourseByCode(String courseCode);

    /**
     * Retrieves all available courses
     *
     * @return a list containing all courses
     */
	List<Course> getAllCourses();
	
	
	Course updateCourse(String courseCode , String name);
	
	void deleteCourse(String courseCode);
}
