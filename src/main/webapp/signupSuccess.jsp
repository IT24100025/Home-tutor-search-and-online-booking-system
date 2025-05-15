<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up Successful - Student Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f0f2f5;
        }
        .container {
            max-width: 400px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #1a73e8;
        }
        .message {
            color: #4caf50;
            margin: 20px 0;
        }
        .student-id {
            font-size: 1.2em;
            font-weight: bold;
            color: #1a73e8;
            margin: 20px 0;
            padding: 10px;
            background-color: #f8f9fa;
            border-radius: 4px;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            background-color: #1a73e8;
            color: white;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #1557b0;
        }
    </style>
</head>
<body>
    <%
        String studentId = (String) session.getAttribute("newStudentId");
        if (studentId == null) {
            response.sendRedirect("signup.jsp");
            return;
        }
        // Remove the student ID from session after displaying
        session.removeAttribute("newStudentId");
    %>
    <div class="container">
        <h1>Welcome!</h1>
        <p class="message">Sign Up Successful</p>
        <p>Your Student ID is:</p>
        <p class="student-id"><%= studentId %></p>
        <p>Please save this ID for future login.</p>
        <a href="index.jsp" class="button">Go to Main Page</a>
    </div>
</body>
</html> 