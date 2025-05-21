<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add New Payment</title>
  <style>
    form { max-width: 400px; margin: 20px auto; }
    label { display: block; margin: 10px 0; }
    input { width: 100%; padding: 8px; }
    button { padding: 8px 15px; background: #4CAF50; color: white; border: none; }
  </style>
</head>
<body>
<h1 style="text-align: center;">Add New Payment</h1>
<form action="${pageContext.request.contextPath}/add-payment" method="post">
  <label>Payment ID: <input type="text" name="id" required></label>
  <label>Student Name: <input type="text" name="name" required></label>
  <label>Amount: <input type="number" step="0.01" name="amount" required></label>
  <label>Date: <input type="date" name="date" required></label>
  <button type="submit">Save Payment</button>
</form>
<p style="text-align: center;"><a href="view-payments.jsp">Back to Payments</a></p>
</body>
</html>