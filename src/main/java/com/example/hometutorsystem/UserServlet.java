package com.example.hometutorsystem;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        try {
            // Check if username already exists
            for (User user : FileHandler.readAllUsers()) {
                if (user.getUsername().equals(username)) {
                    // For AJAX - return JSON error
                    if (isAjax(request)) {
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\":\"Username already exists\"}");
                        return;
                    } else {
                        request.setAttribute("error", "Username already exists");
                        request.getRequestDispatcher("/register.html").forward(request, response);
                        return;
                    }
                }
            }

            User newUser = new User(username, password, email, phone, address);
            FileHandler.saveUser(newUser);

            // For AJAX - return success
            if (isAjax(request)) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\":true}");
                return;
            }
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error registering user");
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            for (User user : FileHandler.readAllUsers()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);

                    // Check if this user is a tutor
                    boolean isTutor = false;
                    for (Tutor tutor : FileHandler.readAllTutors()) {
                        if (tutor.getUsername().equals(username)) {
                            isTutor = true;
                            break;
                        }
                    }

                    session.setAttribute("isTutor", isTutor);
                    response.sendRedirect("dashboard.html");
                    return;
                }
            }

            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.html").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        try {
            User updatedUser = new User(username, password, email, phone, address);
            FileHandler.updateUser(updatedUser);
            response.sendRedirect("dashboard.html");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating user");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");

        try {
            FileHandler.deleteUser(username);
            FileHandler.deleteTutor(username); // Also delete tutor profile if exists
            session.invalidate();
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting user");
        }
    }
}
