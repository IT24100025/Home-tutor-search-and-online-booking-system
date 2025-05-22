package com.example.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/studentDetails")
public class StudentDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\manuka\\BackEnd\\src\\main\\resources\\Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(line.split("\\|"));
            }
        } catch (IOException e) {
            request.setAttribute("message", "Error reading student data");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("/student_details.jsp").forward(request, response);
    }
}