<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Account - Confirm</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md text-center">
    <h1 class="text-2xl font-bold text-blue-600 mb-6">Are you sure you want to delete your account?</h1>
    <form action="${pageContext.request.contextPath}/delete" method="post" class="space-y-4">
        <input type="hidden" name="action" value="confirm">
        <div class="flex space-x-4">
            <button type="submit" name="choice" value="yes" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600">Yes</button>
            <button type="submit" name="choice" value="no" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600">No</button>
        </div>
    </form>
</div>
</body>
</html>