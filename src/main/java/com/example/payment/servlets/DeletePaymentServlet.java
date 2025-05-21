package com.example.payment.servlets;

import com.example.payment.model.PaymentManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/delete-payment")
public class DeletePaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            PaymentManager.deletePayment(id);
            response.sendRedirect("view-payments");
        } catch (Exception e) {
            response.sendRedirect("view-payments?error=" + e.getMessage());
        }
    }
}