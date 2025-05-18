package TutorSystem;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;

import java.io.IOException;

@WebServlet("/booking")
public class BookingManagementServlet extends HttpServlet {
    private BookingManager manager;
    private Gson gson;

    @Override
    public void init() {
        manager = new BookingManager();
        manager.getAllBookings().addAll(FileHandler.loadBookings());
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookings", manager.getAllBookings());
        request.setAttribute("sortedSubjects", manager.getSortedSubjects());
        request.setAttribute("subjectCategoriesJson", gson.toJson(manager.getSubjectCategories()));
        request.getRequestDispatcher("/booking.jsp").forward(request, response);
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



        FileHandler.overwriteBookings(manager.getAllBookings());

        response.sendRedirect("booking");
    }
}


