package com.tutorsystem.tutorbooking.util;

import com.tutorsystem.tutorbooking.bst.BST;
import com.tutorsystem.tutorbooking.model.Tutor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String TUTORS_FILE = "tutors.txt";
    private static BST tutorsBST = new BST();
    private static boolean isBSTInitialized = false;

    // Initialize BST from file
    private static synchronized void initializeBST() throws IOException {
        if (!isBSTInitialized) {
            List<String> tutors = getAllTutorsFromFile();
            for (String tutorData : tutors) {
                String[] parts = tutorData.split("\\|");
                if (parts.length >= 12) {
                    tutorsBST.insert(new Tutor(parts));
                }
            }
            isBSTInitialized = true;
        }
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
        initializeBST();
        String[] parts = tutorData.split("\\|");
        if (parts.length >= 12) {
            Tutor tutor = new Tutor(parts);
            tutorsBST.insert(tutor);

            // Also save to file
            try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE, true))) {
                out.println(tutorData);
            }
        }
    }

    public static synchronized List<String> getAllTutors() throws IOException {
        initializeBST();
        List<Tutor> tutorList = new ArrayList<>();
        tutorsBST.inOrder(tutorList);

        List<String> result = new ArrayList<>();
        for (Tutor tutor : tutorList) {
            result.add(tutor.toFileString());
        }
        return result;
    }

    public static synchronized void updateTutor(String tutorId, String updatedData) throws IOException {
        initializeBST();
        String[] parts = updatedData.split("\\|");
        if (parts.length >= 12) {
            Tutor updatedTutor = new Tutor(parts);

            // Update in BST
            Tutor existing = tutorsBST.searchById(tutorId);
            if (existing != null) {
                // Update all fields except ID
                existing.setEmail(updatedTutor.getEmail());
                existing.setPassword(updatedTutor.getPassword());
                // Update all other fields...

                // Update in file
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
        initializeBST();
        Tutor tutor = tutorsBST.searchById(tutorId);
        return tutor != null ? tutor.toFileString() : null;
    }

    public static synchronized String findTutorByEmail(String email) throws IOException {
        initializeBST();
        Tutor tutor = tutorsBST.searchByEmail(email);
        return tutor != null ? tutor.toFileString() : null;
    }
}