package com.tutorsystem.tutorbooking.util;

import com.tutorsystem.tutorbooking.bst.BST;
import com.tutorsystem.tutorbooking.model.Tutor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String TUTORS_FILE = "C:/Users/fzasl/Downloads/OOP/Project/TutorBooking/data/tutors.txt";
    private static final String ID_COUNTER_FILE = "C:/Users/fzasl/Downloads/OOP/Project/TutorBooking/data/id_counter.txt";
    private static BST tutorsBST = new BST();
    private static boolean isBSTInitialized = false;
    private static int lastTutorId = 0;

    // Initialize BST from file and last used ID - called once at startup
    public static synchronized void loadAllTutors() throws IOException {
        if (!isBSTInitialized) {
            loadLastTutorId();
            List<String> tutors = getAllTutorsFromFile();
            initializeLastId(tutors);

            for (String tutorData : tutors) {
                String[] parts = tutorData.split("\\|");
                if (parts.length >= 12) {
                    insertTutor(new Tutor(parts));
                }
            }
            isBSTInitialized = true;
        }
    }

    // Separate synchronized method for tutor insertion
    public static synchronized void insertTutor(Tutor tutor) {
        tutorsBST.insert(tutor);
    }

    // Initialize the last used ID from existing tutors
    private static synchronized void initializeLastId(List<String> tutors) {
        for (String tutor : tutors) {
            String[] parts = tutor.split("\\|");
            if (parts.length > 0 && parts[0].startsWith("T")) {
                try {
                    int idNum = Integer.parseInt(parts[0].substring(1));
                    if (idNum > lastTutorId) {
                        lastTutorId = idNum;
                    }
                } catch (NumberFormatException e) {
                    // Ignore improperly formatted IDs
                }
            }
        }
    }

    private static void loadLastTutorId() throws IOException {
        File file = new File(ID_COUNTER_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    lastTutorId = Integer.parseInt(line);
                }
            }
        }
    }

    private static void saveLastTutorId() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(ID_COUNTER_FILE))) {
            out.println(lastTutorId);
        }
    }

    // Generate a new sequential tutor ID
    public static synchronized String generateTutorId() {
        lastTutorId++;
        return String.format("T%06d", lastTutorId);
    }

    private static List<String> getAllTutorsFromFile() throws IOException {
        List<String> tutors = new ArrayList<>();
        File file = new File(TUTORS_FILE);

        if (!file.exists()) {
            file.createNewFile();
            return tutors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tutors.add(line);
                }
            }
        }
        return tutors;
    }

    public static synchronized void saveTutor(String tutorData) throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        String[] parts = tutorData.split("\\|");
        if (parts.length >= 12) {
            String tutorId = generateTutorId();
            parts[0] = tutorId;
            tutorData = String.join("|", parts);

            Tutor tutor = new Tutor(parts);
            insertTutor(tutor); // Changed from tutorsBST.insert()

            try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE, true))) {
                out.println(tutorData);
            }
            saveLastTutorId();
        }
    }

    public static synchronized String saveTutorAndGetId(String tutorData) throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        String[] parts = tutorData.split("\\|");
        if (parts.length >= 12) {
            String tutorId = generateTutorId();
            parts[0] = tutorId;
            tutorData = String.join("|", parts);

            Tutor tutor = new Tutor(parts);
            insertTutor(tutor); // Changed from tutorsBST.insert()

            try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE, true))) {
                out.println(tutorData);
            }
            saveLastTutorId();
            return tutorId;
        }
        return null;
    }

    public static synchronized List<String> getAllTutors() throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        List<Tutor> tutorList = new ArrayList<>();
        tutorsBST.inOrder(tutorList);

        List<String> result = new ArrayList<>();
        for (Tutor tutor : tutorList) {
            result.add(tutor.toFileString());
        }
        return result;
    }

    public static synchronized void updateTutor(String tutorId, String updatedData) throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        String[] parts = updatedData.split("\\|");
        if (parts.length >= 12) {
            Tutor updatedTutor = new Tutor(parts);

            Tutor existing = tutorsBST.searchById(tutorId);
            if (existing != null) {
                // Update all fields except ID
                existing.setEmail(updatedTutor.getEmail());
                existing.setPassword(updatedTutor.getPassword());
                // Update other fields as needed...

                List<String> tutors = getAllTutorsFromFile();
                try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE))) {
                    for (String tutor : tutors) {
                        String[] tParts = tutor.split("\\|");
                        if (tParts.length > 0 && tParts[0].equals(tutorId)) {
                            out.println(updatedData);
                        } else {
                            out.println(tutor);
                        }
                    }
                }
            }
        }
    }

    public static synchronized String findTutorById(String tutorId) throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        Tutor tutor = tutorsBST.searchById(tutorId);
        return tutor != null ? tutor.toFileString() : null;
    }

    public static synchronized String findTutorByEmail(String email) throws IOException {
        loadAllTutors(); // Changed from initializeBST()
        Tutor tutor = tutorsBST.searchByEmail(email);
        return tutor != null ? tutor.toFileString() : null;
    }
}