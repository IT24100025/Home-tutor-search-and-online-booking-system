package servlet;

import dao.PaymentDAO;
import model.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment/update")
public class PaymentUpdateServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() {
        paymentDAO = new PaymentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("id");
        Payment payment = paymentDAO.getPaymentById(paymentId);
        request.setAttribute("payment", payment);
        request.getRequestDispatcher("/payment_update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originalPaymentId = request.getParameter("originalPaymentId");
        String paymentId = request.getParameter("paymentId");
        String studentName = request.getParameter("studentName");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");
        String cardNumber = request.getParameter("cardNumber").replace(" ", "");
        String expiry = request.getParameter("expiry");
        String cvc = request.getParameter("cvc");

        Payment updatedPayment = new Payment(paymentId, studentName, amount, date, cardNumber, expiry, cvc);
        paymentDAO.updatePayment(originalPaymentId, updatedPayment);

        response.sendRedirect(request.getContextPath() + "/payment/list");
    }
}
