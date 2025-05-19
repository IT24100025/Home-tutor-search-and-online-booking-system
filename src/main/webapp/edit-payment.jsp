<%@ page import="com.example.payment.model.Payment" %>
<%
    Payment payment = (Payment) request.getAttribute("payment");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Payment</title>
    <style>
        .error { color: red; }
        form { max-width: 500px; margin: 20px auto; }
        label { display: block; margin: 10px 0; }
        input { width: 100%; padding: 8px; }
        button { padding: 8px 15px; background: #4CAF50; color: white; border: none; }
    </style>
</head>
<body>
<h1>Edit Payment</h1>
<% if (error != null) { %>
<p class="error"><%= error %></p>
<% } %>
<form action="edit-payment" method="post">
    <input type="hidden" name="id" value="<%= payment.getId() %>">

    <label>Student Name:
        <input type="text" name="name" value="<%= payment.getStudentName() %>" required>
    </label>

    <label>Amount:
        <input type="number" step="0.01" name="amount" value="<%= payment.getAmount() %>" required>
    </label>

    <label>Date:
        <input type="date" name="date" value="<%= payment.getDate() %>" required>
    </label>

    <button type="submit">Update Payment</button>
</form>
<p><a href="view-payments">Back to Payments</a></p>
</body>
</html>