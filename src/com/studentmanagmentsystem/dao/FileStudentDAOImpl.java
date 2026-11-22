
package com.studentmanagmentsystem.dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.UncheckedIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

import com.studentmanagmentsystem.model.Student;

public class FileStudentDAOImpl implements StudentDAO  {

    private Path filePath;

    public FileStudentDAOImpl() {
        this.filePath = Paths.get("Students.txt");
    }

    @Override
    public void addStudent(Student student) {
        String data = student.getNationalId()+","+
                      student.getName()+","+
                      student.getAge()+","+
                      student.getGrade();

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            writer.write(data);
            writer.newLine();

        } catch(IOException e) {
            throw new UncheckedIOException("Failed to save student", e);
        }
    }

    @Override
    public boolean deleteStudent(String nationalId) {

        if (Files.notExists(filePath)) {
            return false;
        }

        try {
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = Files.newBufferedReader(
                    filePath, StandardCharsets.UTF_8)) {

                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            boolean isDeleted = lines.removeIf(line -> {
                String[] data = line.split(",");
                return data.length == 4 && data[0].equals(nationalId);
            });

            if (!isDeleted) {
                return false;
            }

            try (BufferedWriter writer = Files.newBufferedWriter(
                    filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE)) {

                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            return true;

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to delete student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Student student = new Student(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        Double.parseDouble(data[3])
                );

                students.add(student);
            }

        } catch (IOException e) {
            System.out.println("Error reading students file: " + e.getMessage());
        }

        return students;
    }

    @Override
    public boolean updateStudent(String nationalId, Student updatedStudent) {

        List<Student> students = getAllStudents();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getNationalId().equals(nationalId)) {

                students.set(i, updatedStudent);
                found = true;
                break;
            }
        }

        if (found) {

            try (BufferedWriter writer = Files.newBufferedWriter(
                    filePath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING)) {

                for (Student student : students) {

                    writer.write(student.getNationalId() + "," +
                            student.getName() + "," +
                            student.getAge() + "," +
                            student.getGrade());

                    writer.newLine();
                }

            } catch (IOException e) {
                System.out.println("Error updating students file: " + e.getMessage());
                return false;
            }
        }

        return found;
    }

    @Override
    public Student findStudentByNationalId(String nationalId) {

        if (Files.notExists(filePath)) {
            return null;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                filePath, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(nationalId)) {
                    return new Student(
                            data[0],
                            data[1],
                            Integer.parseInt(data[2]),
                            Double.parseDouble(data[3])
                    );
                }
            }

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read students", e);
        }

        return null;
    }
}