<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tutor Login - Home Tutor System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>Tutor Login</h1>
        <p>Access your tutor account to manage your profile and bookings</p>
    </div>

    <form id="loginForm" action="TutorLoginServlet" method="POST">
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
            <p>Don't have an account? <a href="tutor-profile.jsp?action=create">Register as Tutor</a></p>
            <p><a href="#">Forgot Password?</a></p>
        </div>
    </form>
</div>

<script src="js/script.js"></script>
</body>
</html>