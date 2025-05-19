package org.example.oop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.oop.Login;
import org.example.oop.Process;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Process process;

    @Override
    public void init() throws ServletException {
        process = new Process();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentID = req.getParameter("studentID");
        String password = req.getParameter("password");

        Login login = process.login;
        login.setLoginStudentID(studentID);
        login.setLoginPassword(password);

        String result = process.loginProcess();
        req.setAttribute("result", result);
        req.getRequestDispatcher("/jsp/login_result.jsp").forward(req, resp);
    }
}