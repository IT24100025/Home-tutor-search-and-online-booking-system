package servlet;

import dao.PaymentDAO;
import model.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/payment/create")
public class PaymentCreateServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() {
        paymentDAO = new PaymentDAO();
    }

    // Handle GET request to show the create payment form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/payment_create.jsp").forward(request, response);
    }

    // Handle POST request to process form data and create a new payment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String studentName = request.getParameter("studentName");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");
        String cardNumber = request.getParameter("cardNumber").replace(" ", ""); // Remove spaces
        String expiry = request.getParameter("expiry");
        String cvc = request.getParameter("cvc");

        Payment payment = new Payment(paymentId, studentName, amount, date, cardNumber, expiry, cvc);
        paymentDAO.createPayment(payment);

        response.sendRedirect(request.getContextPath() + "/payment/list");
    }
}
