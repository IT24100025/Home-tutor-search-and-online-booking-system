<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-4xl mx-auto">
    <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Student Details</h1>
    <table class="w-full border-collapse">
        <thead>
        <tr class="bg-blue-500 text-white">
            <th class="p-2">First Name</th>
            <th class="p-2">Last Name</th>
            <th class="p-2">NID</th>
            <th class="p-2">Address</th>
            <th class="p-2">Phone Number</th>
            <th class="p-2">Date of Birth</th>
            <th class="p-2">Gender</th>
            <th class="p-2">Admin ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr class="border-b">
                <td class="p-2">${student[0]}</td>
                <td class="p-2">${student[1]}</td>
                <td class="p-2">${student[2]}</td>
                <td class="p-2">${student[3]}</td>
                <td class="p-2">${student[4]}</td>
                <td class="p-2">${student[5]}</td>
                <td class="p-2">${student[6]}</td>
                <td class="p-2">${student[7]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="main" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600 transition mt-4">Back to Main</a>
</div>
</body>
</html>