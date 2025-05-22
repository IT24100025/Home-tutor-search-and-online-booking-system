package com.example.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private Process process = new Process();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String adminId = request.getParameter("adminId");
            String password = request.getParameter("password");

            process.delete.setDeleteAdminID(adminId);
            process.delete.setDeleteAdminPassword(password);

            String result = process.DeleteLogin();
            request.setAttribute("message", result);

            if (result.equals("Login Successful")) {
                request.getSession().setAttribute("adminId", adminId);
                request.getRequestDispatcher("/delete_confirm.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if ("confirm".equals(action)) {
            String choice = request.getParameter("choice");
            if ("yes".equals(choice)) {
                process.DeleteProcess();
                request.setAttribute("message", "Your account has been deleted");
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            } else {
                response.sendRedirect("main");
            }
        }
    }
}