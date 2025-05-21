package dao;

import model.Payment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private final String FILE_PATH = "C:/Users/damsa/Desktop/sliit/1 year 2 sem/vivas/Payment/src/main/resources/data/payment.txt";

    public void createPayment(Payment payment) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(payment.toString());
            writer.newLine();
        }
    }

    public List<Payment> getAllPayments() throws IOException {
        List<Payment> payments = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return payments;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    Payment payment = new Payment(data[0], data[1], Double.parseDouble(data[2]), data[3], data[4], data[5], data[6]);
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

    // Now accepts originalPaymentId to allow changing the ID
    public void updatePayment(String originalPaymentId, Payment updatedPayment) throws IOException {
        List<Payment> payments = getAllPayments();
        File file = new File(FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Payment payment : payments) {
                if (payment.getPaymentId().equals(originalPaymentId)) {
                    writer.write(updatedPayment.toString());
                } else {
                    writer.write(payment.toString());
                }
                writer.newLine();
            }
        }
    }

    public void deletePayment(String paymentId) throws IOException {
        List<Payment> payments = getAllPayments();
        File file = new File(FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Payment payment : payments) {
                if (!payment.getPaymentId().equals(paymentId)) {
                    writer.write(payment.toString());
                    writer.newLine();
                }
            }
        }
    }

    public Payment getPaymentById(String paymentId) throws IOException {
        List<Payment> payments = getAllPayments();
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }
}
