<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register as Tutor - Home Tutor System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>Register as Tutor</h1>
        <p>Create your tutor profile to start offering your services</p>
    </div>

    <% if (request.getParameter("error") != null) { %>
    <div class="error-message">
        <%= request.getParameter("error") %>
    </div>
    <% } %>

    <form id="registerForm" action="TutorCRUDServlet" method="POST">
        <input type="hidden" name="action" value="create">

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <textarea id="address" name="address" rows="3" required></textarea>
        </div>

        <div class="form-group">
            <label for="phone">Contact Number:</label>
            <input type="tel" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="subjects">Subjects to Teach:</label>
            <input type="text" id="subjects" name="subjects" placeholder="e.g., Math, Science, English" required>
        </div>

        <div class="form-group">
            <label for="grades">Grades to Teach:</label>
            <input type="text" id="grades" name="grades" placeholder="e.g., 1-12, College" required>
        </div>

        <div class="form-group">
            <label for="experience">Years of Experience:</label>
            <input type="number" id="experience" name="experience" min="0" required>
        </div>

        <div class="form-group">
            <label for="hourlyRate">Hourly Rate ($):</label>
            <input type="number" id="hourlyRate" name="hourlyRate" min="0" step="0.01" required>
        </div>

        <div class="form-group">
            <label>Availability:</label>
            <div class="availability-grid">
                <div class="day-time">
                    <label><input type="checkbox" name="monday" value="Monday"> Monday</label>
                    <input type="time" name="mondayStart"> to <input type="time" name="mondayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="tuesday" value="Tuesday"> Tuesday</label>
                    <input type="time" name="tuesdayStart"> to <input type="time" name="tuesdayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="wednesday" value="Wednesday"> Wednesday</label>
                    <input type="time" name="wednesdayStart"> to <input type="time" name="wednesdayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="thursday" value="Thursday"> Thursday</label>
                    <input type="time" name="thursdayStart"> to <input type="time" name="thursdayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="friday" value="Friday"> Friday</label>
                    <input type="time" name="fridayStart"> to <input type="time" name="fridayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="saturday" value="Saturday"> Saturday</label>
                    <input type="time" name="saturdayStart"> to <input type="time" name="saturdayEnd">
                </div>
                <div class="day-time">
                    <label><input type="checkbox" name="sunday" value="Sunday"> Sunday</label>
                    <input type="time" name="sundayStart"> to <input type="time" name="sundayEnd">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="bio">Brief Bio/Introduction:</label>
            <textarea id="bio" name="bio" rows="4" required></textarea>
        </div>

        <button type="submit" class="btn-login">Register</button>
    </form>
</div>
</body>
</html>