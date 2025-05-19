<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Account - Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Edit Account - Login</h1>
    <form action="${pageContext.request.contextPath}/edit" method="post" class="space-y-4">
        <input type="hidden" name="action" value="login">
        <div>
            <label for="studentID" class="block text-sm font-medium text-gray-700">Student ID</label>
            <input type="text" id="studentID" name="studentID" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" id="password" name="password" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600">Login</button>
    </form>
    <a href="${pageContext.request.contextPath}/main" class="block text-center mt-4 text-blue-500 hover:underline">Back to Main</a>
</div>
</body>
</html>