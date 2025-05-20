package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TutorCRUDServlet")
public class TutorCRUDServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                createTutor(request, response);
            } else if ("update".equals(action)) {
                updateTutor(request, response);
            } else {
                response.sendRedirect("tutor-login.jsp?error=Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("tutor-register.jsp?error=Error processing your request");
        }
    }

    private void createTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tutorId = "T" + System.currentTimeMillis();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
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

        String tutorData = String.join("|",
                tutorId, email, password, name, address, phone,
                subjects, grades, experience, hourlyRate,
                availability.toString(), bio);

        FileUtil.saveTutor(tutorData);
        response.sendRedirect("TutorProfileServlet?id=" + tutorId);
    }

    private void updateTutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tutorId = request.getParameter("tutorId");
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

        // Get existing tutor data to preserve email and password
        String existingTutor = FileUtil.findTutorById(tutorId);
        if (existingTutor == null) {
            response.sendRedirect("tutor-login.jsp?error=Tutor not found");
            return;
        }

        String[] parts = existingTutor.split("\\|");
        String email = parts.length > 1 ? parts[1] : "";
        String password = parts.length > 2 ? parts[2] : "";

        String updatedData = String.join("|",
                tutorId, email, password, name, address, phone,
                subjects, grades, experience, hourlyRate,
                availability.toString(), bio);

        FileUtil.updateTutor(tutorId, updatedData);
        response.sendRedirect("TutorProfileServlet?id=" + tutorId + "&message=Profile updated successfully");
    }
}