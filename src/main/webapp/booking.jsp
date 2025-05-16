<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Booking Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <style>
    .booking-form {
      background-color: #f8f9fa;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 0 15px rgba(0,0,0,0.1);
    }
    .table-container {
      margin-top: 2rem;
      background-color: white;
      padding: 1rem;
      border-radius: 10px;
      box-shadow: 0 0 15px rgba(0,0,0,0.1);
    }
    .btn-action {
      margin: 0 2px;
    }
    .page-header {
      color: #2c3e50;
      margin-bottom: 2rem;
      padding-bottom: 1rem;
      border-bottom: 2px solid #3498db;
    }
  </style>
</head>
<body class="bg-light">
<div class="container py-5">
  <h1 class="page-header text-center">Tutor Booking System</h1>

  <div class="row">
    <div class="col-md-4">
      <div class="booking-form">
        <h3 class="mb-4"><i class="fas fa-calendar-plus"></i> New Booking</h3>
        <form method="post" action="booking">
          <div class="mb-3">
            <label class="form-label">Booking ID</label>
            <input type="text" class="form-control" name="bookingId" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Student Name</label>
            <input type="text" class="form-control" name="studentName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Tutor Name</label>
            <input type="text" class="form-control" name="tutorName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Subject</label>
            <input type="text" class="form-control" name="subject" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Date</label>
            <input type="date" class="form-control" name="date" required>
          </div>
          <div class="d-grid gap-2">
            <button type="submit" name="action" value="add" class="btn btn-primary">
              <i class="fas fa-plus"></i> Add Booking
            </button>
            <button type="submit" name="action" value="update" class="btn btn-warning">
              <i class="fas fa-edit"></i> Update Booking
            </button>
          </div>
        </form>
      </div>
    </div>

    <div class="col-md-8">
      <div class="table-container">
        <h3 class="mb-4"><i class="fas fa-list"></i> All Bookings</h3>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Student</th>
              <th>Tutor</th>
              <th>Subject</th>
              <th>Date</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
              java.util.List<TutorSystem.Booking> bookings =
                      (java.util.List<TutorSystem.Booking>) request.getAttribute("bookings");
              for (TutorSystem.Booking b : bookings) {
            %>
            <tr>
              <td><%= b.getBookingId() %></td>
              <td><%= b.getStudentName() %></td>
              <td><%= b.getTutorName() %></td>
              <td><%= b.getSubject() %></td>
              <td><%= b.getDate() %></td>
              <td>
                <form method="post" action="booking" style="display:inline;">
                  <input type="hidden" name="bookingId" value="<%= b.getBookingId() %>">
                  <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm btn-action">
                    <i class="fas fa-trash"></i>
                  </button>
                </form>
              </td>
            </tr>
            <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
