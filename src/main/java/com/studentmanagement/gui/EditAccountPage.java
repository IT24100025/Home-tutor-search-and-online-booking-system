package com.studentmanagement.gui;

import com.studentmanagement.service.StudentManager;
import javax.swing.*;
import java.awt.*;

public class EditAccountPage extends JFrame {
    private final MainPage mainPage;
    private final StudentManager studentManager;
    
    private final JTextField studentIdField;
    private final JPasswordField currentPasswordField;
    private final JTextField newAddressField;
    private final JTextField newPhoneField;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmNewPasswordField;

    public EditAccountPage(MainPage mainPage) {
        this.mainPage = mainPage;
        this.studentManager = new StudentManager();

        setTitle("Edit Account");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components
        studentIdField = new JTextField();
        currentPasswordField = new JPasswordField();
        newAddressField = new JTextField();
        newPhoneField = new JTextField();
        newPasswordField = new JPasswordField();
        confirmNewPasswordField = new JPasswordField();

        // Add components to panel
        mainPanel.add(new JLabel("Student ID:"));
        mainPanel.add(studentIdField);
        mainPanel.add(new JLabel("Current Password:"));
        mainPanel.add(currentPasswordField);
        mainPanel.add(new JLabel("New Address:"));
        mainPanel.add(newAddressField);
        mainPanel.add(new JLabel("New Phone Number:"));
        mainPanel.add(newPhoneField);
        mainPanel.add(new JLabel("New Password:"));
        mainPanel.add(newPasswordField);
        mainPanel.add(new JLabel("Confirm New Password:"));
        mainPanel.add(confirmNewPasswordField);

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back to Main");

        mainPanel.add(updateButton);
        mainPanel.add(backButton);

        // Add action listeners
        updateButton.addActionListener(e -> handleUpdate());
        backButton.addActionListener(e -> {
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        });

        // Add panel to frame
        add(mainPanel);
    }

    private void handleUpdate() {
        String studentId = studentIdField.getText();
        String currentPassword = new String(currentPasswordField.getPassword());
        String newAddress = newAddressField.getText().trim();
        String newPhone = newPhoneField.getText().trim();
        String newPassword = new String(newPasswordField.getPassword());
        String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

        // Validate required fields
        if (studentId.isEmpty() || currentPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Student ID and Current Password are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate new password if provided
        if (!newPassword.isEmpty() && !newPassword.equals(confirmNewPassword)) {
            JOptionPane.showMessageDialog(this,
                    "New passwords do not match!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update account
        boolean success = studentManager.editAccount(
            studentId,
            currentPassword,
            newAddress.isEmpty() ? null : newAddress,
            newPhone.isEmpty() ? null : newPhone,
            newPassword.isEmpty() ? null : newPassword
        );

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Account updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Student ID or Password",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        studentIdField.setText("");
        currentPasswordField.setText("");
        newAddressField.setText("");
        newPhoneField.setText("");
        newPasswordField.setText("");
        confirmNewPasswordField.setText("");
    }
} 