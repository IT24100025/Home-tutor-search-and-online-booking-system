package com.example.hometutorsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String USER_FILE = "users.txt";
    private static final String TUTOR_FILE = "tutors.txt";

    // User CRUD operations
    public static void saveUser(User user) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
            out.println(user.toString());
        }
    }

    public static List<User> readAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        }
        return users;
    }

    public static void updateUser(User updatedUser) throws IOException {
        List<User> users = readAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
        writeAllUsers(users);
    }

    public static void deleteUser(String username) throws IOException {
        List<User> users = readAllUsers();
        users.removeIf(user -> user.getUsername().equals(username));
        writeAllUsers(users);
    }

    private static void writeAllUsers(List<User> users) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                out.println(user.toString());
            }
        }
    }

    // Tutor CRUD operations
    public static void saveTutor(Tutor tutor) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(TUTOR_FILE, true))) {
            out.println(tutor.toString());
        }
    }

    public static List<Tutor> readAllTutors() throws IOException {
        List<Tutor> tutors = new ArrayList<>();
        File file = new File(TUTOR_FILE);
        if (!file.exists()) return tutors;

        try (BufferedReader reader = new BufferedReader(new FileReader(TUTOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tutors.add(Tutor.fromString(line));
            }
        }
        return tutors;
    }

    public static void updateTutor(Tutor updatedTutor) throws IOException {
        List<Tutor> tutors = readAllTutors();
        for (int i = 0; i < tutors.size(); i++) {
            if (tutors.get(i).getUsername().equals(updatedTutor.getUsername())) {
                tutors.set(i, updatedTutor);
                break;
            }
        }
        writeAllTutors(tutors);
    }

    public static void deleteTutor(String username) throws IOException {
        List<Tutor> tutors = readAllTutors();
        tutors.removeIf(tutor -> tutor.getUsername().equals(username));
        writeAllTutors(tutors);
    }

    private static void writeAllTutors(List<Tutor> tutors) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(TUTOR_FILE))) {
            for (Tutor tutor : tutors) {
                out.println(tutor.toString());
            }
        }
    }
}
