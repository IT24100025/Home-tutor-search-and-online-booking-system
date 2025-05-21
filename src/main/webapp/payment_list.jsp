<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Payment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-md">
    <h1 class="text-2xl font-bold mb-4">Payment Methods</h1>
    <a href="<%= request.getContextPath() %>/payment/create" class="bg-blue-500 text-white p-2 rounded mb-4 inline-block hover:bg-blue-600">Add New Payment</a>
    <table class="w-full border-collapse">
        <thead>
        <tr class="bg-gray-200">
            <th class="p-2 border">Payment ID</th>
            <th class="p-2 border">Student Name</th>
            <th class="p-2 border">Amount</th>
            <th class="p-2 border">Date</th>
            <th class="p-2 border">Card Number</th>
            <th class="p-2 border">Expiry</th>
            <th class="p-2 border">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Payment> payments = (List<Payment>) request.getAttribute("payments");
            if (payments != null && !payments.isEmpty()) {
                for (Payment payment : payments) {
        %>
        <tr>
            <td class="p-2 border"><%= payment.getPaymentId() %></td>
            <td class="p-2 border"><%= payment.getStudentName() %></td>
            <td class="p-2 border"><%= payment.getAmount() %></td>
            <td class="p-2 border"><%= payment.getDate() %></td>
            <td class="p-2 border"><%= payment.getCardNumber().replaceAll("(.{4})", "$1 ") %></td>
            <td class="p-2 border"><%= payment.getExpiry() %></td>
            <td class="p-2 border">
                <a href="<%= request.getContextPath() %>/payment/update?id=<%= payment.getPaymentId() %>" class="text-blue-500 mr-2">Edit</a>
                <a href="<%= request.getContextPath() %>/payment/delete?id=<%= payment.getPaymentId() %>" class="text-red-500" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7" class="p-2 text-center">No payments found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
