<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Successful - Student Management System</title>
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
        // Check if user is logged in
        String studentId = (String) session.getAttribute("studentId");
        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="container">
        <h1>Welcome!</h1>
        <p class="message">Login Successful</p>
        <p>Student ID: <%= studentId %></p>
        <a href="index.jsp" class="button">Go to Main Page</a>
    </div>
</body>
</html> 