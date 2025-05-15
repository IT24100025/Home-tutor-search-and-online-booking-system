<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account - Student Management System</title>
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
        }
        h1 {
            text-align: center;
            color: #1a73e8;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            background-color: #1a73e8;
            color: white;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #1557b0;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .error-message {
            color: #d32f2f;
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Account</h1>
        <form action="editProcess.jsp" method="post">
            <div class="form-group">
                <label for="studentId">Student ID:</label>
                <input type="text" id="studentId" name="studentId" required>
            </div>
            <div class="form-group">
                <label for="currentPassword">Current Password:</label>
                <input type="password" id="currentPassword" name="currentPassword" required>
            </div>
            <div class="form-group">
                <label for="newAddress">New Address:</label>
                <input type="text" id="newAddress" name="newAddress">
            </div>
            <div class="form-group">
                <label for="newPhone">New Phone Number:</label>
                <input type="text" id="newPhone" name="newPhone">
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword">
            </div>
            <div class="form-group">
                <label for="confirmNewPassword">Confirm New Password:</label>
                <input type="password" id="confirmNewPassword" name="confirmNewPassword">
            </div>
            <div class="button-container">
                <a href="index.jsp" class="button">Back</a>
                <button type="submit" class="button">Update</button>
            </div>
        </form>
        <% 
            String error = request.getParameter("error");
            if (error != null) {
                if (error.equals("1")) {
        %>
                    <p class="error-message">Invalid Student ID or Password</p>
        <%      } else if (error.equals("2")) { %>
                    <p class="error-message">New passwords do not match</p>
        <%      }
            }
        %>
    </div>
</body>
</html> 