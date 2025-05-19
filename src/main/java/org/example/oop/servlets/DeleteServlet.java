package org.example.oop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.oop.Delete;
import org.example.oop.Process;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private Process process;

    @Override
    public void init() throws ServletException {
        process = new Process();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/delete_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Delete delete = process.delete;

        if ("login".equals(action)) {
            delete.setEnterStudentID(req.getParameter("studentID"));
            delete.setEnterPassword(req.getParameter("password"));
            String result = process.deleteLogin();
            req.setAttribute("result", result);
            if ("Login Successful".equals(result)) {
                req.getRequestDispatcher("/jsp/delete_confirm.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/jsp/delete_result.jsp").forward(req, resp);
            }
        } else if ("confirm".equals(action)) {
            String choice = req.getParameter("choice");
            if ("yes".equals(choice)) {
                process.deleteProcess();
                req.setAttribute("result", "Your account has been deleted");
                req.getRequestDispatcher("/jsp/delete_result.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
            }
        }
    }
}