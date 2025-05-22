package com.example.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private Process process = new Process();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminName = request.getParameter("adminName");
        String adminEmail = request.getParameter("adminEmail");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String adminNIC = request.getParameter("adminNIC");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", "Passwords do not match!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        String adminId = "ADMIN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        process.signUp.setAdminName(adminName);
        process.signUp.setAdminEmail(adminEmail);
        process.signUp.setAddress(address);
        process.signUp.setPhoneNumber(Long.parseLong(phoneNumber));
        process.signUp.setAdminNIC(adminNIC);
        process.signUp.setAdminPassword(password);
        process.signUp.setAdminID(adminId);

        process.SingUpDetail();

        request.setAttribute("adminId", adminId);
        request.getRequestDispatcher("/signup_success.jsp").forward(request, response);
    }
}