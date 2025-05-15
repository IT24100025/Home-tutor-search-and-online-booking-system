package com.studentmanagement.gui;

import com.studentmanagement.service.StudentManager;
import javax.swing.*;
import java.awt.*;

public class DeleteAccountPage extends JFrame {
    private final MainPage mainPage;
    private final StudentManager studentManager;
    
    private final JTextField studentIdField;
    private final JPasswordField passwordField;

    public DeleteAccountPage(MainPage mainPage) {
        this.mainPage = mainPage;
        this.studentManager = new StudentManager();

        setTitle("Delete Account");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components
        studentIdField = new JTextField();
        passwordField = new JPasswordField();

        // Add components to panel
        mainPanel.add(new JLabel("Student ID:"));
        mainPanel.add(studentIdField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);

        JButton deleteButton = new JButton("Delete Account");
        JButton backButton = new JButton("Back to Main");

        mainPanel.add(new JLabel()); // Empty space
        mainPanel.add(deleteButton);
        mainPanel.add(new JLabel()); // Empty space
        mainPanel.add(backButton);

        // Add action listeners
        deleteButton.addActionListener(e -> handleDelete());
        backButton.addActionListener(e -> {
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        });

        // Add panel to frame
        add(mainPanel);
    }

    private void handleDelete() {
        String studentId = studentIdField.getText();
        String password = new String(passwordField.getPassword());

        // Validate fields
        if (studentId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Student ID and Password are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show confirmation dialog
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this account?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            boolean success = studentManager.deleteAccount(studentId, password);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Your account has been deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                setVisible(false);
                mainPage.showMainPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Student ID or Password",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        }
    }

    private void clearFields() {
        studentIdField.setText("");
        passwordField.setText("");
    }
} 