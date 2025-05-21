<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Payment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Payment</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        function formatCardNumber(input) {
            let value = input.value.replace(/\D/g, '').substring(0, 16);
            input.value = value.replace(/(.{4})/g, '$1 ').trim();
        }
        function formatExpiry(input) {
            let value = input.value.replace(/\D/g, '').substring(0, 4);
            if (value.length > 2) {
                input.value = value.substring(0, 2) + '/' + value.substring(2);
            } else {
                input.value = value;
            }
        }
        function validateCardNumber(input) {
            const cardNumber = input.value.replace(/\s+/g, '');
            const errorSpan = document.getElementById('cardNumberError');
            if (cardNumber.length !== 16) {
                errorSpan.textContent = 'Invalid card number: must be exactly 16 digits.';
                return false;
            } else if (!/^\d{16}$/.test(cardNumber)) {
                errorSpan.textContent = 'Invalid card number: must contain only digits.';
                return false;
            } else {
                errorSpan.textContent = '';
                return true;
            }
        }
        function validateForm() {
            const cardInput = document.querySelector('input[name="cardNumber"]');
            return validateCardNumber(cardInput);
        }
    </script>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-2xl mx-auto bg-white p-6 rounded-lg shadow-md">
    <h1 class="text-2xl font-bold mb-4">Update Payment Method</h1>
    <%
        Payment payment = (Payment) request.getAttribute("payment");
        if (payment != null) {
    %>
    <form action="<%= request.getContextPath() %>/payment/update" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="originalPaymentId" value="<%= payment.getPaymentId() %>">
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Payment ID:</label>
            <input type="text" name="paymentId" value="<%= payment.getPaymentId() %>" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Student Name:</label>
            <input type="text" name="studentName" value="<%= payment.getStudentName() %>" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Amount:</label>
            <input type="number" name="amount" step="0.01" value="<%= payment.getAmount() %>" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Date:</label>
            <input type="date" name="date" value="<%= payment.getDate() %>" class="w-full p-2 border rounded" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Card Number:</label>
            <input type="text" name="cardNumber" value="<%= payment.getCardNumber().replaceAll("(.{4})", "$1 ") %>" class="w-full p-2 border rounded" oninput="formatCardNumber(this)" onblur="validateCardNumber(this)" required>
            <span id="cardNumberError" class="text-red-500 text-sm mt-1 block"></span>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">Expiry (MM/YY):</label>
            <input type="text" name="expiry" value="<%= payment.getExpiry() %>" class="w-full p-2 border rounded" oninput="formatExpiry(this)" required>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">CVC:</label>
            <input type="text" name="cvc" maxlength="3" value="<%= payment.getCvc() %>" class="w-full p-2 border rounded" required>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Update Payment</button>
        <a href="<%= request.getContextPath() %>/payment/list" class="text-blue-500 ml-4">Back to List</a>
    </form>
    <%
    } else {
    %>
    <p class="text-red-500">Payment not found.</p>
    <a href="<%= request.getContextPath() %>/payment/list" class="text-blue-500">Back to List</a>
    <%
        }
    %>
</div>
</body>
</html>
