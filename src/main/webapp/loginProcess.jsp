<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.studentmanagement.service.StudentManager" %>
<%
    String studentId = request.getParameter("studentId");
    String password = request.getParameter("password");
    
    StudentManager studentManager = new StudentManager();
    boolean success = studentManager.login(studentId, password);
    
    if (success) {
        session.setAttribute("studentId", studentId);
        response.sendRedirect("loginSuccess.jsp");
    } else {
        response.sendRedirect("login.jsp?error=1");
    }
%> 