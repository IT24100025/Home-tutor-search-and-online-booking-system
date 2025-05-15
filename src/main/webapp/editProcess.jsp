<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.studentmanagement.service.StudentManager" %>
<%
    String studentId = request.getParameter("studentId");
    String currentPassword = request.getParameter("currentPassword");
    String newAddress = request.getParameter("newAddress");
    String newPhone = request.getParameter("newPhone");
    String newPassword = request.getParameter("newPassword");
    String confirmNewPassword = request.getParameter("confirmNewPassword");

    // Check if new password fields match if provided
    if (newPassword != null && !newPassword.isEmpty()) {
        if (!newPassword.equals(confirmNewPassword)) {
            response.sendRedirect("edit.jsp?error=2");
            return;
        }
    }

    StudentManager studentManager = new StudentManager();
    boolean success = studentManager.editAccount(
        studentId,
        currentPassword,
        newAddress.isEmpty() ? null : newAddress,
        newPhone.isEmpty() ? null : newPhone,
        newPassword.isEmpty() ? null : newPassword
    );

    if (success) {
        response.sendRedirect("editSuccess.jsp");
    } else {
        response.sendRedirect("edit.jsp?error=1");
    }
%> 