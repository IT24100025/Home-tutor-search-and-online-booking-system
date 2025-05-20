package com.tutorsystem.tutorbooking.servlet;

import com.tutorsystem.tutorbooking.util.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TutorProfileServlet")
public class TutorProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tutorId = request.getParameter("id");

        if (tutorId != null && !tutorId.isEmpty()) {
            try {
                String tutorData = FileUtil.findTutorById(tutorId);
                if (tutorData != null) {
                    request.setAttribute("tutorData", tutorData);
                    request.getRequestDispatcher("/tutor-profile.jsp").forward(request, response);
                } else {
                    response.sendRedirect("tutor-login.jsp?error=Tutor not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("tutor-login.jsp?error=Error accessing profile");
            }
        } else {
            response.sendRedirect("tutor-login.jsp");
        }
    }
}
