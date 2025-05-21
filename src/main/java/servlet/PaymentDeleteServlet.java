package servlet;

import dao.PaymentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/payment/delete")
public class PaymentDeleteServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() {
        paymentDAO = new PaymentDAO();
    }

    // Handle GET request to delete a payment
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("id");
        paymentDAO.deletePayment(paymentId);
        response.sendRedirect(request.getContextPath() + "/payment/list");
    }
}
