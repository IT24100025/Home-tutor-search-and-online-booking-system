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
            <a href="#" id="logout">Logout</a>
        </nav>
    </header>

    <div id="profileView" class="profile-section">
        <h2>Your Profile Details</h2>
        <div class="profile-details">
            <div class="detail-row">
                <span class="detail-label">Name:</span>
                <span id="viewName" class="detail-value">${tutorData.split("\\|")[3]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Address:</span>
                <span id="viewAddress" class="detail-value">${tutorData.split("\\|")[4]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Contact Number:</span>
                <span id="viewPhone" class="detail-value">${tutorData.split("\\|")[5]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Subjects:</span>
                <span id="viewSubjects" class="detail-value">${tutorData.split("\\|")[6]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Grades to Teach:</span>
                <span id="viewGrades" class="detail-value">${tutorData.split("\\|")[7]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Experience (years):</span>
                <span id="viewExperience" class="detail-value">${tutorData.split("\\|")[8]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Hourly Rate ($):</span>
                <span id="viewRate" class="detail-value">${tutorData.split("\\|")[9]}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Availability:</span>
                <span id="viewAvailability" class="detail-value">${tutorData.split("\\|")[10]}</span>
            </div>
        </div>
        <button id="enableEdit" class="btn-edit">Edit Profile</button>
    </div>

    <div id="profileEdit" class="profile-section" style="display:none;">
        <h2>Edit Your Profile</h2>
        <form id="profileForm" action="TutorProfileServlet" method="POST">
        <input type="hidden" id="action" name="action" value="${param.action == 'create' ? 'create' : 'update'}">
            <input type="hidden" id="tutorId" name="tutorId" value="${tutorData != null ? tutorData.split("\\|")[0] : ''}">

            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" value="${tutorData != null ? tutorData.split("\\|")[3] : ''}" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="3" required>${tutorData != null ? tutorData.split("\\|")[4] : ''}</textarea>
            </div>

            <div class="form-group">
                <label for="phone">Contact Number:</label>
                <input type="tel" id="phone" name="phone" value="${tutorData != null ? tutorData.split("\\|")[5] : ''}" required>
            </div>

            <div class="form-group">
                <label for="subjects">Subjects to Teach (comma separated):</label>
                <input type="text" id="subjects" name="subjects" value="${tutorData != null ? tutorData.split("\\|")[6] : ''}" required>
            </div>

            <div class="form-group">
                <label for="grades">Grades to Teach (e.g., 1-12, College):</label>
                <input type="text" id="grades" name="grades" value="${tutorData != null ? tutorData.split("\\|")[7] : ''}" required>
            </div>

            <div class="form-group">
                <label for="experience">Years of Experience:</label>
                <input type="number" id="experience" name="experience" min="0" value="${tutorData != null ? tutorData.split("\\|")[8] : '0'}" required>
            </div>

            <div class="form-group">
                <label for="hourlyRate">Hourly Rate ($):</label>
                <input type="number" id="hourlyRate" name="hourlyRate" min="0" step="0.01" value="${tutorData != null ? tutorData.split("\\|")[9] : '0'}" required>
            </div>

            <div class="form-group">
                <label>Availability:</label>
                <div class="availability-grid">
                    <!-- Availability checkboxes would need JavaScript to properly set based on the availability string -->
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
                <textarea id="bio" name="bio" rows="4">${tutorData != null && tutorData.split("\\|").length > 11 ? tutorData.split("\\|")[11] : ''}</textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-save">Save Changes</button>
                <button type="button" id="cancelEdit" class="btn-cancel">Cancel</button>
                <button type="button" id="deleteProfile" class="btn-delete">Delete Profile</button>
            </div>
        </form>
    </div>
</div>

<script src="js/script.js"></script>
</body>
</html>