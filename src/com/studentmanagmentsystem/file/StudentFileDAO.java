package com.studentmanagmentsystem.file;

import java.io.IOException;
import java.util.List;
import com.studentmanagmentsystem.model.Student;

public interface StudentFileDAO {

	void saveStudents(List<Student> students) throws IOException;

	List<String> readStudents() throws IOException;

}
