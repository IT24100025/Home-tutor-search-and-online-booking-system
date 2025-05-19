<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up Result</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md text-center">
    <h1 class="text-2xl font-bold text-blue-600 mb-6">Signup Successful</h1>
    <a href="${pageContext.request.contextPath}/main" class="block w-full bg-blue-500 text-white text-center py-2 rounded hover:bg-blue-600">Back to Main</a>
</div>
</body>
</html>