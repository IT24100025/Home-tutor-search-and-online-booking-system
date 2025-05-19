package org.example.oop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.oop.Edit;
import org.example.oop.Process;

import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private Process process;

    @Override
    public void init() throws ServletException {
        process = new Process();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/edit_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Edit edit = process.edit;

        if ("login".equals(action)) {
            edit.setEnterStudentID(req.getParameter("studentID"));
            edit.setEnterPassword(req.getParameter("password"));
            String result = process.editLogin();
            req.setAttribute("result", result);
            if ("Login Successful".equals(result)) {
                req.getRequestDispatcher("/jsp/edit_details.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/jsp/edit_result.jsp").forward(req, resp);
            }
        } else if ("update".equals(action)) {
            try {
                String address = req.getParameter("address");
                long phoneNo = Long.parseLong(req.getParameter("phoneNo"));
                String password = req.getParameter("password");
                String confirmPassword = req.getParameter("confirmPassword");
                if (password.equals(confirmPassword)) {
                    process.editProcess(address, phoneNo, password);
                    req.setAttribute("result", "Edit Successful");
                    req.getRequestDispatcher("/jsp/edit_result.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Passwords do not match");
                    req.getRequestDispatcher("/jsp/edit_details.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid phone number format");
                req.getRequestDispatcher("/jsp/edit_details.jsp").forward(req, resp);
            }
        }
    }
}