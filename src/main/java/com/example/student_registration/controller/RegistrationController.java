package com.example.student_registration.controller;
import com.example.student_registration.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "registration";
    }

    @PostMapping("/register")
    @ResponseBody
    public String submitForm(@ModelAttribute Student student,
                             @RequestParam("photo") MultipartFile photo) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XE", "system", "Pr@thmeshOracle");

            String sql = "INSERT INTO students (name, email, password, phone, dob, gender, department, year_of_study, address) " +
                    "VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPassword());
            stmt.setString(4, student.getPhone());
            stmt.setString(5, student.getDob());
            stmt.setString(6, student.getGender());
            stmt.setString(7, student.getDepartment());
            stmt.setString(8, student.getYear());
            stmt.setString(9, student.getAddress());

            stmt.executeUpdate();
            conn.close();

            return "Registration Successful!";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
