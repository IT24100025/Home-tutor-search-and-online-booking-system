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

@WebServlet("/tutorDetails")
public class TutorDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> tutors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\manuka\\BackEnd\\src\\main\\resources\\Tutors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tutors.add(line.split("\\|"));
            }
        } catch (IOException e) {
            request.setAttribute("message", "Error reading tutor data");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("tutors", tutors);
        request.getRequestDispatcher("/tutor_details.jsp").forward(request, response);
    }
}