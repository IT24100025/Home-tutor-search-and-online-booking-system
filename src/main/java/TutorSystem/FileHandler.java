package TutorSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "src/main/resources/bookings.txt";
    private static final String HEADER = "#  Format: bookingId,studentName,tutorName,subject,date";

    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Create file and directory if they don't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                // Write header
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(HEADER);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error creating bookings file: " + e.getMessage());
                return bookings;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Booking booking = new Booking(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    bookings.add(booking);
                } else {
                    System.err.println("Invalid booking format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading bookings file: " + e.getMessage());
        }
        return bookings;
    }

    public static void saveBooking(Booking booking) {
        File file = new File(FILE_PATH);

        // Create file and directory if they don't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                // Write header
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(HEADER);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error creating bookings file: " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(booking.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving booking: " + e.getMessage());
        }
    }

    public static void overwriteBookings(List<Booking> bookings) {
        File file = new File(FILE_PATH);

        try {
            // Create parent directories if they don't exist
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write header
                writer.write(HEADER);
                writer.newLine();

                // Write all bookings
                for (Booking booking : bookings) {
                    writer.write(booking.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error overwriting bookings: " + e.getMessage());
        }
    }
}
