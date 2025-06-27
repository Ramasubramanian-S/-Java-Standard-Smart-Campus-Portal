package com.example.student_registration.service;

import com.example.student_registration.model.Student;
import java.sql.*;
import java.util.*;

public class StudentQueryService {
    private final Connection connection;

    public StudentQueryService(Connection connection) {
        this.connection = connection;
    }

    // Get all students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getString("gender"));
                student.setDepartment(rs.getString("department"));
                student.setYear(rs.getString("year_of_study"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        }
        return students;
    }

    // Filter by department and year
    public List<Student> getStudentsByFilter(String department, String year) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE department = ? AND year_of_study = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, department);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getString("gender"));
                student.setDepartment(rs.getString("department"));
                student.setYear(rs.getString("year_of_study"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        }
        return students;
    }
}
