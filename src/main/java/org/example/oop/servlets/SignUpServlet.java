package org.example.oop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.oop.Process;
import org.example.oop.SignUp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private Process process;
    private static long studentIdCounter; // Static counter for generating sequential IDs
    private static final String COUNTER_FILE = "D:\\java Ultimate\\Test Project\\OOP_Final_push\\OOP_01\\src\\main\\resources\\data\\student_id_counter.txt"; // File to store counter

    @Override
    public void init() throws ServletException {
        process = new Process();
        // Load counter from file
        try {
            File file = new File(COUNTER_FILE);
            if (file.exists()) {
                String content = Files.readString(Paths.get(COUNTER_FILE)).trim();
                studentIdCounter = Long.parseLong(content);
            } else {
                studentIdCounter = 1; // Initialize if file doesn't exist
            }
        } catch (IOException | NumberFormatException e) {
            throw new ServletException("Failed to load student ID counter", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        SignUp signUp = process.signUp;

        if ("details".equals(action)) {
            signUp.setFirstName(req.getParameter("firstName"));
            signUp.setLastName(req.getParameter("lastName"));
            signUp.setDateOfBirth(req.getParameter("dateOfBirth"));
            signUp.setGender(req.getParameter("gender"));
            signUp.setNID(Long.parseLong(req.getParameter("nid")));
            signUp.setAddress(req.getParameter("address"));
            signUp.setPhoneNo(Long.parseLong(req.getParameter("phoneNo")));

            // Generate student ID in format STD000000001 without incrementing counter
            String studentID = String.format("STD%09d", studentIdCounter);
            signUp.setStudentID(studentID);
            req.setAttribute("studentID", studentID);

            req.getRequestDispatcher("/jsp/signup_id.jsp").forward(req, resp);
        } else if ("password".equals(action)) {
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            if (password.equals(confirmPassword)) {
                signUp.setPassword(password);
                process.signUpProcess();

                // Increment counter and save to file only after successful signup
                studentIdCounter++;
                try {
                    Files.writeString(Paths.get(COUNTER_FILE), String.valueOf(studentIdCounter));
                } catch (IOException e) {
                    throw new ServletException("Failed to save student ID counter", e);
                }

                req.getRequestDispatcher("/jsp/signup_result.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Passwords do not match");
                req.setAttribute("studentID", signUp.getStudentID());
                req.getRequestDispatcher("/jsp/signup_id.jsp").forward(req, resp);
            }
        }
    }
}