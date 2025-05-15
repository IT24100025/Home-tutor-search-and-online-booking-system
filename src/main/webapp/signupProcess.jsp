<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.studentmanagement.service.StudentManager" %>
<%@ page import="com.studentmanagement.model.Student" %>
<%@ page import="java.time.LocalDate" %>
<%
    try {
        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String nicNumber = request.getParameter("nicNumber");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("signup.jsp?error=1");
            return;
        }

        // Create student object
        Student student = new Student(firstName, lastName, dob, gender, nicNumber, address, phoneNumber);
        
        // Register student
        StudentManager studentManager = new StudentManager();
        student = studentManager.signUp(student, password);

        // Store student ID in session and redirect to success page
        session.setAttribute("newStudentId", student.getStudentId());
        response.sendRedirect("signupSuccess.jsp");
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("signup.jsp?error=2");
    }
%> 