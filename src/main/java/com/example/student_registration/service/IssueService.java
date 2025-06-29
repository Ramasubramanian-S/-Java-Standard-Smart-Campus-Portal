package com.example.student_registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class IssueService {

    private static final Logger logger = LoggerFactory.getLogger(IssueService.class);

    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/xepdb1";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "power";

    public boolean reportIssue(String studentId, String description) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO issues (student_id, description, reported_at) VALUES (?, ?, SYSDATE)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, description);

            int rows = stmt.executeUpdate();
            logger.info("Issue reported successfully by student ID: " + studentId);
            return rows > 0;

        } catch (Exception e) {
            logger.error("Error while reporting issue for student ID: " + studentId, e);
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                logger.info("Database connection closed in IssueService.");
            } catch (SQLException ex) {
                logger.warn("Failed to close database resources in IssueService", ex);
            }
        }
    }
}
