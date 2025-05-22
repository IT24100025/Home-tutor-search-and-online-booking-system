package com.example.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Process process = new Process();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminId = request.getParameter("adminId");
        String password = request.getParameter("password");

        process.login.setEnterAdminID(adminId);
        process.login.setEnterAdminPassword(password);

        String result = process.LoginProcess();
        request.setAttribute("message", result);

        if (result.equals("Login Successful")) {
            request.getSession().setAttribute("adminId", adminId);
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else if (result.equals("Your Account is Deleted")) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}