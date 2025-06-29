package com.example.student_registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class FacultyDashboardService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/xepdb1";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "power";

    public boolean assignGrade(String studentEmail, String course, String grade) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "UPDATE student_grades SET grade = ? WHERE student_email = ? AND course = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, grade);
            stmt.setString(2, studentEmail);
            stmt.setString(3, course);

            int rows = stmt.executeUpdate();
            logger.info("Grade assigned for " + studentEmail + " in " + course + ": " + grade);
            return rows > 0;

        } catch (Exception e) {
            logger.error("Error assigning grade for " + studentEmail, e);
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                logger.warn("Failed to close resources", ex);
            }
        }
    }
}
