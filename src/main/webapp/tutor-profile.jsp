<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.tutorsystem.tutorbooking.util.FileUtil" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tutor Profile - Home Tutor System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="profile-container">
    <header class="profile-header">
        <h1>Tutor Profile Management</h1>
        <nav>
            <a href="#" id="viewProfile">View Profile</a>
            <a href="#" id="editProfile">Edit Profile</a>
            <a href="index.jsp" id="logout">Logout</a>
        </nav>
    </header>

    <%
        String tutorData = (String) request.getAttribute("tutorData");
        if (tutorData == null) {
            response.sendRedirect("tutor-login.jsp");
            return;
        }
        String[] tutorInfo = tutorData.split("\\|");
    %>

    <div id="profileView" class="profile-section">
        <h2>Your Profile Details</h2>

        <% if (request.getParameter("message") != null) { %>
        <div class="success-message">
            <%= request.getParameter("message") %>
        </div>
        <% } %>

        <div class="profile-details">
            <div class="detail-row">
                <span class="detail-label">Name:</span>
                <span class="detail-value"><%= tutorInfo.length > 3 ? tutorInfo[3] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Email:</span>
                <span class="detail-value"><%= tutorInfo.length > 1 ? tutorInfo[1] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Address:</span>
                <span class="detail-value"><%= tutorInfo.length > 4 ? tutorInfo[4] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Contact Number:</span>
                <span class="detail-value"><%= tutorInfo.length > 5 ? tutorInfo[5] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Subjects:</span>
                <span class="detail-value"><%= tutorInfo.length > 6 ? tutorInfo[6] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Grades to Teach:</span>
                <span class="detail-value"><%= tutorInfo.length > 7 ? tutorInfo[7] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Experience:</span>
                <span class="detail-value"><%= tutorInfo.length > 8 ? tutorInfo[8] + " years" : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Hourly Rate:</span>
                <span class="detail-value">$<%= tutorInfo.length > 9 ? tutorInfo[9] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Availability:</span>
                <span class="detail-value"><%= tutorInfo.length > 10 ? tutorInfo[10] : "" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">About Me:</span>
                <span class="detail-value"><%= tutorInfo.length > 11 ? tutorInfo[11] : "" %></span>
            </div>
        </div>
        <button id="enableEdit" class="btn-edit">Edit Profile</button>
    </div>

    <div id="profileEdit" class="profile-section" style="display:none;">
        <h2>Edit Your Profile</h2>
        <form id="profileForm" action="TutorCRUDServlet" method="POST">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="tutorId" value="<%= tutorInfo[0] %>">

            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" value="<%= tutorInfo.length > 3 ? tutorInfo[3] : "" %>" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="3" required><%= tutorInfo.length > 4 ? tutorInfo[4] : "" %></textarea>
            </div>

            <div class="form-group">
                <label for="phone">Contact Number:</label>
                <input type="tel" id="phone" name="phone" value="<%= tutorInfo.length > 5 ? tutorInfo[5] : "" %>" required>
            </div>

            <div class="form-group">
                <label for="subjects">Subjects to Teach:</label>
                <input type="text" id="subjects" name="subjects" value="<%= tutorInfo.length > 6 ? tutorInfo[6] : "" %>" required>
            </div>

            <div class="form-group">
                <label for="grades">Grades to Teach:</label>
                <input type="text" id="grades" name="grades" value="<%= tutorInfo.length > 7 ? tutorInfo[7] : "" %>" required>
            </div>

            <div class="form-group">
                <label for="experience">Years of Experience:</label>
                <input type="number" id="experience" name="experience" min="0" value="<%= tutorInfo.length > 8 ? tutorInfo[8] : "0" %>" required>
            </div>

            <div class="form-group">
                <label for="hourlyRate">Hourly Rate ($):</label>
                <input type="number" id="hourlyRate" name="hourlyRate" min="0" step="0.01" value="<%= tutorInfo.length > 9 ? tutorInfo[9] : "0" %>" required>
            </div>

            <div class="form-group">
                <label>Availability:</label>
                <div class="availability-grid">
                    <!-- This would need JavaScript to properly set the checkboxes and times based on the availability string -->
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
                <textarea id="bio" name="bio" rows="4"><%= tutorInfo.length > 11 ? tutorInfo[11] : "" %></textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-save">Save Changes</button>
                <button type="button" id="cancelEdit" class="btn-cancel">Cancel</button>
            </div>
        </form>
    </div>
</div>

<script src="js/script.js"></script>
<script>
    document.getElementById('enableEdit').addEventListener('click', function() {
        document.getElementById('profileView').style.display = 'none';
        document.getElementById('profileEdit').style.display = 'block';
    });

    document.getElementById('cancelEdit').addEventListener('click', function() {
        document.getElementById('profileEdit').style.display = 'none';
        document.getElementById('profileView').style.display = 'block';
    });
</script>
</body>
</html>