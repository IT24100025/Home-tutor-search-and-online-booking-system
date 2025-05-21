package com.example.payment.servlets;

import com.example.payment.model.Payment;
import com.example.payment.model.PaymentManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddPaymentServlet", value = "/add-payment")
public class AddPaymentServlet extends HttpServlet {

    // Add this method to handle GET requests if needed
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("add-payment.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String date = request.getParameter("date");

            Payment payment = new Payment(id, name, amount, date, "Active");
            PaymentManager.addPayment(payment);

            response.sendRedirect(request.getContextPath() + "/view-payments");
        } catch (Exception e) {
            request.setAttribute("error", "Error adding payment: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}