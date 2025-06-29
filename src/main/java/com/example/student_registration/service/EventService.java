package com.example.student_registration.service;

//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/xepdb1";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "power";

    public boolean createEvent(String title, String date, String description) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO events (title, event_date, description) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, title);
            stmt.setString(2, date);
            stmt.setString(3, description);

            int rows = stmt.executeUpdate();
            logger.info("Event created successfully: " + title);
            return rows > 0;

        } catch (Exception e) {
            logger.error("Error creating event: " + title, e);
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                logger.warn("Failed to close database resources", ex);
            }
        }
    }
}
