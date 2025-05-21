<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error</title>
  <style>
    .error { color: red; text-align: center; margin: 50px auto; max-width: 600px; }
  </style>
</head>
<body>
<div class="error">
  <h1>Error Occurred</h1>
  <p>${error}</p>
  <p><a href="view-payments.jsp">Back to Payments</a></p>
</div>
</body>
</html>