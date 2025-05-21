package com.example.payment.servlets;

import com.example.payment.model.Payment;
import com.example.payment.model.PaymentManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/edit-payment")
public class EditPaymentServlet extends HttpServlet {

    // Handle GET request to show edit form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Payment payment = PaymentManager.getPaymentById(id);

            if (payment == null) {
                response.sendRedirect("view-payments?error=Payment+not+found");
                return;
            }

            request.setAttribute("payment", payment);
            request.getRequestDispatcher("/edit-payment.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("view-payments?error=" + e.getMessage());
        }
    }

    // Handle POST request to process edits
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String date = request.getParameter("date");

            Payment updatedPayment = new Payment(id, name, amount, date, "Active");
            PaymentManager.updatePayment(id, updatedPayment);

            response.sendRedirect("view-payments");
        } catch (Exception e) {
            response.sendRedirect("edit-payment?id=" + request.getParameter("id") + "&error=" + e.getMessage());
        }
    }
}