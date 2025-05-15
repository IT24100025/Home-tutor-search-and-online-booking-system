package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/TutorLoginServlet")
public class TutorLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // In a real application, you would validate the password securely
        // For this example, we'll just check if the tutor exists

        try {
            String tutorData = FileUtil.findTutorByEmail(email);

            if (tutorData != null) {
                // Successful login - redirect to profile page with tutor ID
                String[] parts = tutorData.split("\\|");
                String tutorId = parts[0];
                response.sendRedirect("tutor-profile.html?action=view&id=" + tutorId);
            } else {
                // Failed login
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("tutor-login.html").forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during login");
            request.getRequestDispatcher("tutor-login.html").forward(request, response);
        }
    }
}