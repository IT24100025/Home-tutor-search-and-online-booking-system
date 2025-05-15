<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Login - Home Tutor System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>Student Login</h1>
        <p>Access your student account to find tutors and manage bookings</p>
    </div>

    <form id="loginForm" action="StudentLoginServlet" method="POST">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn-login">Login</button>

        <div class="form-footer">
            <p>Don't have an account? <a href="student-register.jsp">Register as Student</a></p>
            <p><a href="#">Forgot Password?</a></p>
        </div>
    </form>
</div>

<script src="js/script.js"></script>
</body>
</html>