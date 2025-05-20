package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TutorLoginServlet")
public class TutorLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            String tutorData = FileUtil.findTutorByEmail(email);

            if (tutorData != null) {
                // Simple password check (in real app, use proper password hashing)
                String[] parts = tutorData.split("\\|");
                if (parts.length > 2 && parts[2].equals(password)) {
                    response.sendRedirect("TutorProfileServlet?id=" + parts[0]);
                } else {
                    response.sendRedirect("tutor-login.jsp?error=Invalid password");
                }
            } else {
                response.sendRedirect("tutor-login.jsp?error=Tutor not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("tutor-login.jsp?error=Login error");
        }
    }
}
