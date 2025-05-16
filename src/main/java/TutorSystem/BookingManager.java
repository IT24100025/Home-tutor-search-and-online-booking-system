package TutorSystem;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void deleteBooking(String id) {
        bookings.removeIf(b -> b.getBookingId().equals(id));
    }

    public Booking getBooking(String id) {
        return bookings.stream().filter(b -> b.getBookingId().equals(id)).findFirst().orElse(null);
    }

    public void updateBooking(Booking updated) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equals(updated.getBookingId())) {
                bookings.set(i, updated);
                break;
            }
        }
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}