<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tutor Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-4xl mx-auto">
    <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Tutor Details</h1>
    <table class="w-full border-collapse">
        <thead>
        <tr class="bg-blue-500 text-white">
            <th class="p-2">Admin ID</th>
            <th class="p-2">Email</th>
            <th class="p-2">Full Name</th>
            <th class="p-2">Address</th>
            <th class="p-2">Phone Number</th>
            <th class="p-2">Subject</th>
            <th class="p-2">Grade</th>
            <th class="p-2">Experience</th>
            <th class="p-2">Hourly Rate</th>
            <th class="p-2">Availability</th>
            <th class="p-2">Bio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tutor" items="${tutors}">
            <tr class="border-b">
                <td class="p-2">${tutor[0]}</td>
                <td class="p-2">${tutor[1]}</td>
                <td class="p-2">${tutor[3]}</td>
                <td class="p-2">${tutor[4]}</td>
                <td class="p-2">${tutor[5]}</td>
                <td class="p-2">${tutor[6]}</td>
                <td class="p-2">${tutor[7]}</td>
                <td class="p-2">${tutor[8]}</td>
                <td class="p-2">${tutor[9]}</td>
                <td class="p-2">${tutor[10]}</td>
                <td class="p-2">${tutor[11]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="main" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600 transition mt-4">Back to Main</a>
</div>
</body>
</html>