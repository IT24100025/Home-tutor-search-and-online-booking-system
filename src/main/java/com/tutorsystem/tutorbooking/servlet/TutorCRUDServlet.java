package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/TutorCRUDServlet")
public class TutorCRUDServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                createTutor(request, response);
            } else if ("update".equals(action)) {
                updateTutor(request, response);
            } else if ("delete".equals(action)) {
                deleteTutor(request, response);
            } else {
                response.sendRedirect("tutor-login.html?error=Invalid action");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("tutor-login.html?error=Error processing your request");
        }
    }

    private void createTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Generate a simple ID (in a real app, use a better method)
        String tutorId = "T" + System.currentTimeMillis();
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In real app, hash this
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String subjects = request.getParameter("subjects");
        String grades = request.getParameter("grades");
        String experience = request.getParameter("experience");
        String hourlyRate = request.getParameter("hourlyRate");
        String bio = request.getParameter("bio");

        // Build availability string
        StringBuilder availability = new StringBuilder();
        String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (String day : days) {
            if (request.getParameter(day) != null) {
                String start = request.getParameter(day + "Start");
                String end = request.getParameter(day + "End");
                if (start != null && end != null && !start.isEmpty() && !end.isEmpty()) {
                    if (availability.length() > 0) availability.append(", ");
                    availability.append(day.substring(0, 1).toUpperCase()).append(day.substring(1))
                            .append(" ").append(start).append("-").append(end);
                }
            }
        }

        // Format tutor data for storage (pipe-separated)
        String tutorData = String.join("|",
                tutorId, email, password, name, address, phone,
                subjects, grades, experience, hourlyRate,
                availability.toString(), bio);

        // Save to file
        FileUtil.saveTutor(tutorData);

        // Redirect to profile view
        response.sendRedirect("tutor-profile.html?action=view&id=" + tutorId);
    }

    private void updateTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tutorId = request.getParameter("tutorId");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String subjects = request.getParameter("subjects");
        String grades = request.getParameter("grades");
        String experience = request.getParameter("experience");
        String hourlyRate = request.getParameter("hourlyRate");
        String bio = request.getParameter("bio");

        // Build availability string
        StringBuilder availability = new StringBuilder();
        String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (String day : days) {
            if (request.getParameter(day) != null) {
                String start = request.getParameter(day + "Start");
                String end = request.getParameter(day + "End");
                if (start != null && end != null && !start.isEmpty() && !end.isEmpty()) {
                    if (availability.length() > 0) availability.append(", ");
                    availability.append(day.substring(0, 1).toUpperCase()).append(day.substring(1))
                            .append(" ").append(start).append("-").append(end);
                }
            }
        }

        // Get existing tutor data to preserve password
        String existingTutor = FileUtil.findTutorById(tutorId);
        if (existingTutor == null) {
            response.sendRedirect("tutor-login.html?error=Tutor not found");
            return;
        }

        String[] parts = existingTutor.split("\\|");
        String password = parts.length > 2 ? parts[2] : "";

        // Format updated tutor data
        String updatedData = String.join("|",
                tutorId, email, password, name, address, phone,
                subjects, grades, experience, hourlyRate,
                availability.toString(), bio);

        // Update in file
        FileUtil.updateTutor(tutorId, updatedData);

        // Redirect to profile view
        response.sendRedirect("tutor-profile.html?action=view&id=" + tutorId);
    }

    private void deleteTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tutorId = request.getParameter("tutorId");

        // Delete from file
        FileUtil.deleteTutor(tutorId);

        // Redirect to login page
        response.sendRedirect("tutor-login.html?message=Your profile has been deleted");
    }
}