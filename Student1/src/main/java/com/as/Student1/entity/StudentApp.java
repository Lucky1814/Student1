package com.as.Student1.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentApp {
    private Connection connection;

    public StudentApp() {
        //  database connection
        try {
            String url = "jdbc:mysql://localhost:3306/ums";
            String username = "root";
            String password = "root123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getStudents(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        try {
            int offset = (pageNumber - 1) * pageSize;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM students ORDER BY id LIMIT ? OFFSET ?"
            );
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();
            List<StudentEntity> students = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int totalMarks = resultSet.getInt("total_marks");
                students.add(new StudentEntity(id, name, totalMarks));
            }

            return ResponseEntity.ok(students);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentEntity>> filterStudents(
            @RequestParam String filterCriteria
    ) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM students WHERE name ILIKE ?"
            );
            statement.setString(1, "%" + filterCriteria + "%");

            ResultSet resultSet = statement.executeQuery();
            List<StudentEntity> students = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int totalMarks = resultSet.getInt("total_marks");
                students.add(new StudentEntity(id, name, totalMarks));
            }

            return ResponseEntity.ok(students);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   
}
