<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign Up - Set Password</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
  <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Set Your Password</h1>
  <p class="text-center text-gray-700 mb-4">Your Student ID: <strong>${studentID}</strong></p>
  <% if (request.getAttribute("error") != null) { %>
  <p class="text-red-500 text-center">${error}</p>
  <% } %>
  <form action="${pageContext.request.contextPath}/signup" method="post" class="space-y-4">
    <input type="hidden" name="action" value="password">
    <div>
      <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
      <input type="password" id="password" name="password" required class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500">
    </div>
    <div>
      <label for="confirmPassword" class="block text-sm font-medium text-gray-700">Confirm Password</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500">
    </div>
    <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600">Sign Up</button>
  </form>
  <a href="${pageContext.request.contextPath}/main" class="block text-center mt-4 text-blue-500 hover:underline">Back to Main</a>
</div>
</body>
</html>