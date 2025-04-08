package com.example.hometutorsystem;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TutorServlet", value = "/tutor")

public class TutorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "register":
                registerTutor(request, response);
                break;
            case "update":
                updateTutor(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("tutors", FileHandler.readAllTutors());
            request.getRequestDispatcher("/tutor-list.html").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving tutors");
        }
    }

    private void registerTutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String grades = request.getParameter("grades");
        int experience = Integer.parseInt(request.getParameter("experience"));
        String subjects = request.getParameter("subjects");
        String availability = request.getParameter("availability");

        try {
            Tutor newTutor = new Tutor(name, address, phone, grades, experience, subjects, availability, username);
            FileHandler.saveTutor(newTutor);
            session.setAttribute("isTutor", true);
            response.sendRedirect("dashboard.html");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error registering tutor");
        }
    }

    private void updateTutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String grades = request.getParameter("grades");
        int experience = Integer.parseInt(request.getParameter("experience"));
        String subjects = request.getParameter("subjects");
        String availability = request.getParameter("availability");

        try {
            Tutor updatedTutor = new Tutor(name, address, phone, grades, experience, subjects, availability, username);
            FileHandler.updateTutor(updatedTutor);
            response.sendRedirect("dashboard.html");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating tutor");
        }
    }
}

