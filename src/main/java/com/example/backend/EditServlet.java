package com.example.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private Process process = new Process();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String adminId = request.getParameter("adminId");
            String password = request.getParameter("password");

            process.edit.setEditAdminID(adminId);
            process.edit.setEditAdminPassword(password);

            String result = process.EditLogin();
            request.setAttribute("message", result);

            if (result.equals("Login Successful")) {
                request.getSession().setAttribute("adminId", adminId);
                request.getRequestDispatcher("/edit_form.jsp").forward(request, response);
            } else if (result.equals("Your Account is Deleted!")) {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            String adminId = (String) request.getSession().getAttribute("adminId");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                request.setAttribute("message", "Passwords do not match!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            process.EditProcess(email, address, Long.parseLong(phoneNumber), password);
            request.setAttribute("message", "Edit Successful");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        }
    }
}