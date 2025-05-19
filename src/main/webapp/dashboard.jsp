<%@ page import="com.example.payment.model.Payment" %>
<%@ page import="java.util.List" %>
<%
  List<Payment> payments = (List<Payment>) request.getAttribute("payments");
  if (payments == null) {
    response.sendRedirect("view-payments");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Payment Management Dashboard</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f5f5f5;
    }
    .container {
      max-width: 1200px;
      margin: 0 auto;
      background: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: #333;
      text-align: center;
      margin-bottom: 30px;
    }
    .action-buttons {
      display: flex;
      justify-content: center;
      gap: 15px;
      margin-bottom: 20px;
    }
    .btn {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-weight: bold;
      text-decoration: none;
      display: inline-block;
      text-align: center;
    }
    .btn-primary {
      background-color: #4CAF50;
      color: white;
    }
    .btn-secondary {
      background-color: #2196F3;
      color: white;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
      font-weight: bold;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
    .action-cell {
      display: flex;
      gap: 10px;
    }
    .btn-sm {
      padding: 5px 10px;
      font-size: 14px;
    }
    .btn-warning {
      background-color: #ff9800;
      color: white;
    }
    .btn-danger {
      background-color: #f44336;
      color: white;
    }
    .search-filter {
      margin-bottom: 20px;
      display: flex;
      gap: 10px;
    }
    .search-filter input, .search-filter select {
      padding: 8px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Payment Management Dashboard</h1>

  <div class="action-buttons">
    <a href="add-payment.jsp" class="btn btn-primary">Add New Payment</a>
    <a href="view-payments" class="btn btn-secondary">Refresh List</a>
  </div>

  <div class="search-filter">
    <input type="text" id="searchInput" placeholder="Search payments...">
    <select id="statusFilter">
      <option value="">All Statuses</option>
      <option value="Active">Active</option>
      <option value="Cancelled">Cancelled</option>
      <option value="Completed">Completed</option>
    </select>
  </div>

  <table id="paymentsTable">
    <thead>
    <tr>
      <th>Payment ID</th>
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
      <td class="action-cell">
        <a href="edit-payment?id=<%= payment.getId() %>" class="btn btn-warning btn-sm">Edit</a>
        <form action="delete-payment" method="post" style="display: inline;">
          <input type="hidden" name="id" value="<%= payment.getId() %>">
          <button type="submit" class="btn btn-danger btn-sm">Delete</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>

<script>
  // Search and filter functionality
  document.getElementById('searchInput').addEventListener('input', filterPayments);
  document.getElementById('statusFilter').addEventListener('change', filterPayments);

  function filterPayments() {
    const searchText = document.getElementById('searchInput').value.toLowerCase();
    const statusFilter = document.getElementById('statusFilter').value;
    const rows = document.querySelectorAll('#paymentsTable tbody tr');

    rows.forEach(row => {
      const cells = row.cells;
      const studentName = cells[1].textContent.toLowerCase();
      const paymentId = cells[0].textContent.toLowerCase();
      const status = cells[4].textContent;

      const matchesSearch = studentName.includes(searchText) || paymentId.includes(searchText);
      const matchesStatus = statusFilter === '' || status === statusFilter;

      row.style.display = (matchesSearch && matchesStatus) ? '' : 'none';
    });
  }
</script>
</body>
</html>