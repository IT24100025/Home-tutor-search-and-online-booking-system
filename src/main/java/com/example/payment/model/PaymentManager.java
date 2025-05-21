package com.example.payment.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {
    private static final String FILE_PATH = "C:\\Users\\damsa\\Desktop\\sliit\\1 year 2 sem\\OOP\\WEEK 14\\Payment\\target\\payments.txt";

    public static synchronized void addPayment(Payment payment) throws IOException {
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(payment.toString());
            writer.newLine();
        }
    }
    public static Payment getPaymentById(String id) throws IOException {
        List<Payment> payments = getAllPayments();
        return payments.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static synchronized List<Payment> getAllPayments() throws IOException {
        ensureFileExists();
        List<Payment> payments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    payments.add(Payment.fromString(line));
                }
            }
        }
        return payments;
    }

    public static synchronized void updatePayment(String id, Payment updatedPayment) throws IOException {
        List<Payment> payments = getAllPayments();
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId().equals(id)) {
                payments.set(i, updatedPayment);
                break;
            }
        }
        saveAllPayments(payments);
    }

    public static synchronized void deletePayment(String id) throws IOException {
        List<Payment> payments = getAllPayments();
        payments.removeIf(p -> p.getId().equals(id));
        saveAllPayments(payments);
    }

    private static void saveAllPayments(List<Payment> payments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Payment payment : payments) {
                writer.write(payment.toString());
                writer.newLine();
            }
        }
    }

    private static void ensureFileExists() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }


}