<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Payment</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        // Format card number to groups of 4 digits
        function formatCardNumber(input) {
            let value = input.value.replace(/\D/g, '').substring(0, 16); // Only digits, max 16
            input.value = value.replace(/(.{4})/g, '$1 ').trim();
        }

        // Format expiry to MM/YY
        function formatExpiry(input) {
            let value = input.value.replace(/\D/g, '').substring(0, 4); // Only digits, max 4
            if (value.length > 2) {
                input.value = value.substring(0, 2) + '/' + value.substring(2);
            } else {
                input.value = value;
            }
        }
    </script>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-2xl mx-auto bg-white p-6 rounded-lg shadow-md">
    <h1 class="text-2xl font-bold mb-4">Add New Payment Method</h1>
    <form action="<%= request.getContextPath() %>/payment/create" method="post">
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Payment ID:</label>
            <input type="text" name="paymentId" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Student Name:</label>
            <input type="text" name="studentName" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Amount:</label>
            <input type="number" name="amount" step="0.01" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Date:</label>
            <input type="date" name="date" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Card Number:</label>
            <input type="text" name="cardNumber" class="w-full p-2 border rounded" oninput="formatCardNumber(this)" placeholder="1234 5678 9012 3456" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Expiry (MM/YY):</label>
            <input type="text" name="expiry" class="w-full p-2 border rounded" oninput="formatExpiry(this)" placeholder="MM/YY" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">CVC:</label>
            <input type="text" name="cvc" maxlength="3" class="w-full p-2 border rounded" required>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Save Payment</button>
        <a href="<%= request.getContextPath() %>/payment/list" class="text-blue-500 ml-4">Back to List</a>
    </form>
</div>
</body>
</html>
