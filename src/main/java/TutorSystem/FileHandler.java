package TutorSystem;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_PATH = "bookings.dat";

    public static void saveBookings(List<Booking> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Booking> loadBookings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Booking>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}