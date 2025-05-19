<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Student Management System</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
  <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Student Management System</h1>
  <div class="space-y-4">
    <a href="${pageContext.request.contextPath}/login" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600">Login</a>
    <a href="${pageContext.request.contextPath}/signup" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600">Sign Up</a>
    <a href="${pageContext.request.contextPath}/edit" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600">Edit Account</a>
    <a href="${pageContext.request.contextPath}/delete" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600">Delete Account</a>
  </div>
</div>
</body>
</html>