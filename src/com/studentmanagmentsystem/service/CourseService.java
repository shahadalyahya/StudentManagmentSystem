package com.studentmanagmentsystem.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.studentmanagmentsystem.dao.CourseDAO;
import com.studentmanagmentsystem.model.Course;

public class CourseService {

	private static final Logger logger = LogManager.getLogger(CourseService.class);
	private final CourseDAO courseDAO;

	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
		logger.info("CourseService initialized successfully");
	}

	public void addCourse(Course course) {

		logger.debug("Starting course addition");

		validateCourse(course);

		if (courseDAO.findCourseByCode(course.getCourseCode()) != null) {

			logger.warn("Cannot add course. Course already exists: {}", course.getCourseCode());

			throw new IllegalArgumentException("Course already exists");
		}

		courseDAO.addCourse(course);

		logger.info("Course added successfully: {}", course.getCourseCode());
	}

	public List<Course> getAllCourses() {

		logger.debug("Retrieving all courses.");

		List<Course> courses = courseDAO.getAllCourses();

		logger.info("Retrieved {} courses successfully.", courses.size());

		return courses;
	}

	/**
	 * Finds a course by its unique course code.
	 *
	 * @param courseCode the unique code of the course
	 * @return the matching course, or null if not found
	 */
	public Course findCourseByCode(String courseCode) {

		validateCourseCode(courseCode);

		logger.debug("Searching for course: {}", courseCode);

		Course course = courseDAO.findCourseByCode(courseCode);

		if (course == null) {

			logger.warn("Course not found: {}", courseCode);

		} else {

			logger.info("Course retrieved successfully: {}", courseCode);
		}

		return course;
	}
	
	public void updateCourse(String courseCode, String name) {

	    logger.info("Entering updateCourse method");

	    validateCourseCode(courseCode);

	    logger.debug("Course code validated successfully: {}", courseCode);

	    courseDAO.updateCourse(courseCode, name);

	    logger.info("Exiting updateCourse method");
	}
	
	public void deleteCourse(String courseCode) {

	    logger.info("Entering deleteCourse method");

	    validateCourseCode(courseCode);

	    logger.debug("Course code validated successfully: {}", courseCode);

	    courseDAO.deleteCourse(courseCode);

	    logger.info("Exiting deleteCourse method");
	}

	/**
	 * Validates the course object and its required fields
	 *
	 * @param course the course to be validated
	 */
	private void validateCourse(Course course) {

		if (course == null) {

			logger.warn("Course validation failed: Course is null.");

			throw new IllegalArgumentException("Course cannot be null");
		}

		validateCourseCode(course.getCourseCode());

		if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {

			logger.warn("Course validation failed: Course name is empty. Code: {}", course.getCourseCode());

			throw new IllegalArgumentException("Course name cannot be empty");
		}
	}

	/**
	 * Validates that the course code is not null or empty
	 *
	 * @param courseCode the course code to be validated
	 */
	private void validateCourseCode(String courseCode) {

		if (courseCode == null || courseCode.trim().isEmpty()) {

			logger.warn("Course code validation failed: Code is null or empty.");

			throw new IllegalArgumentException("Course code cannot be empty");
		}
	}
}
