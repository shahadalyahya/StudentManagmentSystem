package com.studentmanagmentsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.studentmanagmentsystem.dao.CourseDAO;
import com.studentmanagmentsystem.model.Course;

public class CourseDAOImpl implements CourseDAO {

	private static final Logger logger = LogManager.getLogger(CourseDAOImpl.class);
	private final List<Course> courses;

	public CourseDAOImpl() {
		this.courses = new ArrayList<>();
		logger.info("CourseDAOImpl initialized successfully");

	}

	@Override
	public void addCourse(Course course) {

		logger.debug("Attempting to add course: {}", course.getCourseCode());

		if (findCourseByCode(course.getCourseCode()) != null) {

			logger.warn("Course already exists. Course code: {}", course.getCourseCode());

			throw new IllegalArgumentException("Course already exists");
		}

		courses.add(course);

		logger.info("Course added successfully. Course code: {}", course.getCourseCode());
	}

	@Override
	public Course findCourseByCode(String courseCode) {

		logger.debug("Searching for course. Course code: {}", courseCode);

		for (Course course : courses) {

			if (course.getCourseCode().equals(courseCode)) {

				logger.debug("Course found. Course code: {}", courseCode);

				return course;
			}
		}

		logger.debug("Course not found. Course code: {}", courseCode);

		return null;
	}

	@Override
	public List<Course> getAllCourses() {

		logger.info("Retrieving all courses. Total courses: {}", courses.size());

		return new ArrayList<>(courses);
	}

	@Override
	public Course updateCourse(String courseCode, String name) {

	    logger.info("Entering updateCourse method");

	    Course course = findCourseByCode(courseCode);

	    if (course == null) {
	        logger.warn("Course not found. Course code: {}", courseCode);
	        throw new IllegalArgumentException("Course not found");
	    }

	    course.setCourseName(name);

	    logger.info("Course updated successfully. Course code: {}", courseCode);

	    logger.info("Exiting updateCourse method");

	    return course;
	}

	@Override
	public void deleteCourse(String courseCode) {

	    logger.info("Entering deleteCourse method");

	    Course course = findCourseByCode(courseCode);

	    if (course == null) {
	        logger.warn("Course not found. Course code: {}", courseCode);
	        throw new IllegalArgumentException("Course not found");
	    }

	    courses.remove(course);

	    logger.info("Course deleted successfully. Course code: {}", courseCode);

	    logger.info("Exiting deleteCourse method");
	}
	
	
}
