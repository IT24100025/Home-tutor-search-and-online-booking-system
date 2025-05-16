package TutorSystem;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/booking")
public class BookingManagementServlet extends HttpServlet {
    private BookingManager manager;

    @Override
    public void init() {
        manager = new BookingManager();
        manager.getAllBookings().addAll(FileHandler.loadBookings());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookings", manager.getAllBookings());
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("bookingId");

        if ("add".equals(action)) {
            Booking b = new Booking(
                    id,
                    request.getParameter("studentName"),
                    request.getParameter("tutorName"),
                    request.getParameter("subject"),
                    request.getParameter("date")
            );
            manager.addBooking(b);
        } else if ("delete".equals(action)) {
            manager.deleteBooking(id);
        } else if ("update".equals(action)) {
            Booking b = new Booking(
                    id,
                    request.getParameter("studentName"),
                    request.getParameter("tutorName"),
                    request.getParameter("subject"),
                    request.getParameter("date")
            );
            manager.updateBooking(b);
        }

        FileHandler.saveBookings(manager.getAllBookings());
        response.sendRedirect("booking");
    }
}

