package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/TutorProfileServlet")
public class TutorProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tutorId = request.getParameter("id");

        if (tutorId != null && !tutorId.isEmpty()) {
            try {
                String tutorData = FileUtil.findTutorById(tutorId);

                if (tutorData != null) {
                    request.setAttribute("tutorData", tutorData);
                    request.getRequestDispatcher("/tutor-profile.jsp").forward(request, response);
                } else {
                    response.sendRedirect("tutor-login.html?error=Tutor not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.sendRedirect("tutor-login.html?error=Error accessing tutor data");
            }
        } else {
            response.sendRedirect("tutor-login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tutorId = request.getParameter("tutorId");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String subjects = request.getParameter("subjects");
        String grades = request.getParameter("grades");
        String experience = request.getParameter("experience");
        String hourlyRate = request.getParameter("hourlyRate");
        String bio = request.getParameter("bio");

        try {
            // Update the tutor data (assuming you have a method to update the data)
            String updatedData = tutorId + "|" + name + "|" + address + "|" + phone + "|" + subjects + "|" + grades + "|" + experience + "|" + hourlyRate + "|" + bio;
            FileUtil.updateTutor(tutorId, updatedData);

            // Redirect back to the profile page
            response.sendRedirect("tutor-profile.jsp?id=" + tutorId + "&message=Profile updated successfully");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("tutor-profile.jsp?id=" + tutorId + "&error=Error updating profile");
        }
    }
}
