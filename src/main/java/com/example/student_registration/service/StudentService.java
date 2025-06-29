package com.example.student_registration.service;

import com.example.student_registration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/xepdb1";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "power";

    public boolean registerStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            logger.info("Attempting DB connection: " + DB_URL + " with user: " + DB_USER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Database connection successful.");

            String sql = "INSERT INTO students " +
                    "(name, email, password, phone, dob, gender, department, year_of_study, address) " +
                    "VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPassword());
            stmt.setString(4, student.getPhone());
            stmt.setString(5, student.getDob());
            stmt.setString(6, student.getGender());
            stmt.setString(7, student.getDepartment());
            stmt.setString(8, student.getYear());
            stmt.setString(9, student.getAddress());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                logger.info("Student registered successfully: " + student.getEmail());
                return true;
            } else {
                logger.warn("Student registration failed for: " + student.getEmail());
                return false;
            }

        } catch (Exception e) {
            logger.error("Error while registering student: " + student.getEmail(), e);
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                logger.info("Database connection closed.");
            } catch (SQLException ex) {
                logger.warn("Failed to close DB resources", ex);
            }
        }
    }
}
