package com.example.payment.servlets;

import com.example.payment.model.Payment;
import com.example.payment.model.PaymentManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewPaymentsServlet", value = {"/view-payments", "/"})
public class ViewPaymentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Payment> payments = PaymentManager.getAllPayments();
            request.setAttribute("payments", payments);

            // Forward to dashboard if it exists, otherwise use view-payments.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            if (dispatcher == null) {
                dispatcher = request.getRequestDispatcher("/view-payments.jsp");
            }
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error loading payments: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}