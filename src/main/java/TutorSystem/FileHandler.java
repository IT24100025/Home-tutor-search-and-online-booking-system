package TutorSystem;



import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "bookings.txt";

    public static void saveBookings(List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Booking b : bookings) {
                // Customize the format as needed
                String line = b.getId() + "," +
                        b.getStudentName() + "," +
                        b.getTutorName() + "," +
                        b.getSubject() + "," +
                        b.getDate();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Booking> loadBookings() {
        List<Booking> bookings = new java.util.ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Booking b = new Booking(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4]
                    );
                    bookings.add(b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
