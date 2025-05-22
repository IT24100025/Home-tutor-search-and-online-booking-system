<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm Deletion</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Confirm Account Deletion</h1>
    <p class="text-center text-gray-700 mb-4">Are you sure you want to delete your account?</p>
    <form action="delete" method="post" class="space-y-4">
        <input type="hidden" name="action" value="confirm">
        <div class="flex space-x-4">
            <button type="submit" name="choice" value="yes" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition">Yes</button>
            <button type="submit" name="choice" value="no" class="w-full bg-gray-500 text-white py-2 rounded hover:bg-gray-600 transition">No</button>
        </div>
    </form>
    <a href="main" class="block text-center text-blue-500 mt-4 hover:underline">Back to Main</a>
</div>
</body>
</html>