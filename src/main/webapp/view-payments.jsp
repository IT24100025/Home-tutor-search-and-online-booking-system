<%@ page import="com.example.payment.model.PaymentManager,com.example.payment.model.Payment,java.util.List" %>
<%
    List<Payment> payments;
    try {
        payments = PaymentManager.getAllPayments();
    } catch (Exception e) {
        request.setAttribute("error", "Error loading payments: " + e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }
%>
<html>
<head>
    <title>Payment Records</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .actions a { margin-right: 10px; color: #4CAF50; }
    </style>
</head>
<body>
<h1 style="text-align: center;">Payment Records</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Student Name</th>
        <th>Amount</th>
        <th>Date</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (Payment payment : payments) { %>
    <tr>
        <td><%= payment.getId() %></td>
        <td><%= payment.getStudentName() %></td>
        <td>$<%= String.format("%.2f", payment.getAmount()) %></td>
        <td><%= payment.getDate() %></td>
        <td><%= payment.getStatus() %></td>
        <td class="actions">
            <a href="edit-payment?id=<%= payment.getId() %>" class="btn btn-warning btn-sm">Edit</a>
            <form action="delete-payment" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= payment.getId() %>">
                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<p style="text-align: center;">
    <a href="add-payment.jsp" style="padding: 8px 15px; background: #4CAF50; color: white; text-decoration: none;">Add New Payment</a>
</p>
</body>
</html>