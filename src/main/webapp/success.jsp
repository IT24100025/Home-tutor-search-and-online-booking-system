<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">${message}</h1>
    <c:if test="${message == 'Login Successful'}">
        <div class="space-y-4">
            <a href="studentDetails" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600 transition">Student Details</a>
            <a href="tutorDetails" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600 transition">Tutor Details</a>
        </div>
    </c:if>
    <a href="main" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600 transition mt-4">Back to Main</a>
</div>
</body>
</html>