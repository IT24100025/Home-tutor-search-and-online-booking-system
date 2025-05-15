package com.tutorsystem.tutorbooking.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String TUTORS_FILE = "C:/Users/fzasl/Downloads/OOP/Project/TutorBooking/data/tutors.txt";

    public static synchronized void saveTutor(String tutorData) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE, true))) {
            out.println(tutorData);
        }
    }

    public static synchronized List<String> getAllTutors() throws IOException {
        List<String> tutors = new ArrayList<>();
        File file = new File(TUTORS_FILE);

        if (!file.exists()) {
            file.createNewFile();
            return tutors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tutors.add(line);
            }
        }
        return tutors;
    }

    public static synchronized void updateTutor(String tutorId, String updatedData) throws IOException {
        List<String> tutors = getAllTutors();
        boolean found = false;

        try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE))) {
            for (String tutor : tutors) {
                String[] parts = tutor.split("\\|");
                if (parts.length > 0 && parts[0].equals(tutorId)) {
                    out.println(updatedData);
                    found = true;
                } else {
                    out.println(tutor);
                }
            }
        }

        if (!found) {
            throw new IOException("Tutor not found with ID: " + tutorId);
        }
    }

    public static synchronized void deleteTutor(String tutorId) throws IOException {
        List<String> tutors = getAllTutors();
        boolean found = false;

        try (PrintWriter out = new PrintWriter(new FileWriter(TUTORS_FILE))) {
            for (String tutor : tutors) {
                String[] parts = tutor.split("\\|");
                if (parts.length > 0 && parts[0].equals(tutorId)) {
                    found = true;
                    continue; // Skip writing this line (delete)
                }
                out.println(tutor);
            }
        }

        if (!found) {
            throw new IOException("Tutor not found with ID: " + tutorId);
        }
    }

    public static synchronized String findTutorById(String tutorId) throws IOException {
        List<String> tutors = getAllTutors();

        for (String tutor : tutors) {
            String[] parts = tutor.split("\\|");
            if (parts.length > 0 && parts[0].equals(tutorId)) {
                return tutor;
            }
        }
        return null;
    }

    public static synchronized String findTutorByEmail(String email) throws IOException {
        List<String> tutors = getAllTutors();

        for (String tutor : tutors) {
            String[] parts = tutor.split("\\|");
            if (parts.length > 1 && parts[1].equals(email)) {
                return tutor;
            }
        }
        return null;
    }
}
