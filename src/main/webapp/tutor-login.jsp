<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tutor Login - Home Tutor System</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <img src="https://via.placeholder.com/80" alt="Logo" class="logo">
        <h1>Tutor Login</h1>
        <p>Access your tutor account to manage your profile and bookings</p>
    </div>

    <% if (request.getParameter("error") != null) { %>
    <div class="error-message">
        <%= request.getParameter("error") %>
    </div>
    <% } %>

    <form id="loginForm" action="TutorLoginServlet" method="POST">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>

        <button type="submit" class="btn-login">Login</button>

        <div class="form-footer">
            <p>Don't have an account? <a href="tutor-register.jsp">Register as Tutor</a></p>
            <p><a href="#">Forgot Password?</a></p>
        </div>
    </form>
</div>
</body>
</html>