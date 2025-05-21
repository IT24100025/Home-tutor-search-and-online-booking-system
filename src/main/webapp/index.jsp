<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Tutor Search & Online Booking</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<!-- Header Section -->
<header class="bg-blue-600 text-white p-4 shadow-md">
    <div class="max-w-6xl mx-auto flex justify-between items-center">
        <h1 class="text-3xl font-bold">Home Tutor Search & Online Booking</h1>
        <nav>
            <ul class="flex space-x-6">
                <li><a href="<%= request.getContextPath() %>/index.jsp" class="hover:text-blue-200">Home</a></li>
                <li><a href="<%= request.getContextPath() %>/payment/list" class="hover:text-blue-200">Payment Management</a></li>
                <li><a href="#" class="hover:text-blue-200">Tutor Search (Coming Soon)</a></li>
                <li><a href="#" class="hover:text-blue-200">Book a Tutor (Coming Soon)</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- Main Content Section -->
<main class="max-w-6xl mx-auto p-6">
    <section class="bg-white rounded-lg shadow-md p-8 text-center mb-8">
        <h2 class="text-2xl font-semibold mb-4">Welcome to Home Tutor Search</h2>
        <p class="text-gray-700 mb-6">
            Find the perfect tutor for your learning needs and book sessions online with ease.
            Manage your payments securely and keep track of all transactions in one place.
        </p>
        <a href="<%= request.getContextPath() %>/payment/list" class="bg-blue-500 text-white p-3 rounded hover:bg-blue-600">
            Manage Payments
        </a>
        <a href="#" class="bg-green-500 text-white p-3 rounded hover:bg-green-600 ml-4">
            Find a Tutor (Coming Soon)
        </a>
    </section>

    <section class="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div class="bg-white rounded-lg shadow-md p-6 text-center">
            <h3 class="text-xl font-semibold mb-3">Payment Management</h3>
            <p class="text-gray-600 mb-4">Add, view, update, or delete your payment methods securely.</p>
            <a href="<%= request.getContextPath() %>/payment/list" class="text-blue-500 hover:underline">Go to Payments</a>
        </div>
        <div class="bg-white rounded-lg shadow-md p-6 text-center">
            <h3 class="text-xl font-semibold mb-3">Tutor Search</h3>
            <p class="text-gray-600 mb-4">Search for qualified tutors based on subjects and availability.</p>
            <a href="#" class="text-blue-500 hover:underline">Coming Soon</a>
        </div>
        <div class="bg-white rounded-lg shadow-md p-6 text-center">
            <h3 class="text-xl font-semibold mb-3">Online Booking</h3>
            <p class="text-gray-600 mb-4">Book tutoring sessions online with just a few clicks.</p>
            <a href="#" class="text-blue-500 hover:underline">Coming Soon</a>
        </div>
    </section>
</main>

<!-- Footer Section -->
<footer class="bg-gray-800 text-white p-4 text-center">
    <p>&copy; 2025 Home Tutor Search & Online Booking. All rights reserved.</p>
</footer>
</body>
</html>
